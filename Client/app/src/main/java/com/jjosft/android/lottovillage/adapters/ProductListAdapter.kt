package com.jjosft.android.lottovillage.adapters

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.jjosft.android.lottovillage.R
import com.jjosft.android.lottovillage.activities.StoreDetailActivity
import com.jjosft.android.lottovillage.base.BaseApplication
import com.jjosft.android.lottovillage.model.Model
import com.yarolegovich.lovelydialog.LovelyStandardDialog
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.RequestBody
import org.json.JSONObject

/**
 * Created by JJSOFT-DESKTOP on 2017-09-04.
 */
class ProductListAdapter(private val mContext: StoreDetailActivity, private val mDetailsOfProductList: ArrayList<Model.DetailsOfProductList>) : RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {
    private val mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cvMain: CardView = view.findViewById(R.id.store_detail_cv_main);
        val tvProductName: TextView = view.findViewById(R.id.store_detail_tv_product_name)
        val tvProductContents: TextView = view.findViewById(R.id.store_detail_tv_product_contents)
        val tvProductPrice: TextView = view.findViewById(R.id.store_detail_tv_product_price)
        val tvProductStatus: TextView = view.findViewById(R.id.store_detail_tv_product_status)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_product_detail, parent, false)
        return ViewHolder(view)
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProductListAdapter.ViewHolder, position: Int) {
        val productList = mDetailsOfProductList[position]

        holder.tvProductName.text = productList.productName
        holder.tvProductName.tag = productList.productCode
        holder.tvProductContents.text = productList.productContents
        holder.tvProductPrice.text = productList.productPrice.toString()

        when (productList.productStatus) {
            "Y" -> {
                holder.tvProductStatus.text = "판매중"
                holder.tvProductStatus.setTextColor(ContextCompat.getColor(mContext, R.color.blue))

                holder.cvMain.setOnClickListener({
                    LovelyStandardDialog(mContext)
                            .setTopColorRes(R.color.colorPrimary)
                            .setButtonsColorRes(R.color.colorPrimaryDark)
                            .setIcon(R.drawable.ic_add_alert_black_24dp)
                            .setTitle("상품을 구매하시겠습니까?")
                            .setMessage("${productList.productCode}번 상품 \n${productList.productName}을 ${productList.productPrice}에 구매하시겠습니까?")
                            .setPositiveButton(R.string.okay, {
                                buyProduct(productList.productCode, productList.productPrice)
                            })
                            .setNegativeButton(R.string.cancel, null)
                            .show()
                })
            }
            "N" -> {
                holder.tvProductStatus.text = "매진"
                holder.tvProductStatus.setTextColor(ContextCompat.getColor(mContext, R.color.red))
            }
        }
    }

    override fun getItemCount(): Int {
        return mDetailsOfProductList.size
    }

    private fun buyProduct(productCode : Int, productPrice : Int) {
        val jsonObject = JSONObject()
        jsonObject.put("product_code", productCode)
        jsonObject.put("product_price", productPrice)

        BaseApplication.getInstance().getRetrofitMethod().postBuyProduct(RequestBody.create(BaseApplication.MEDIA_TYPE_JSON, jsonObject.toString()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Model.DefaultResponse> {
                    override fun onSubscribe(d: Disposable) {
                        mCompositeDisposable.add(d)
                        BaseApplication.getInstance().progressOn(mContext, "상품을 구매 중 입니다")
                    }

                    override fun onNext(t: Model.DefaultResponse) {
                        if (t.isSuccess) {
                            Toast.makeText(mContext, "상품 구매 성공", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(mContext, t.errorMessage, Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onError(e: Throwable) {
                        Toast.makeText(mContext, "실패 ${e.message}", Toast.LENGTH_SHORT).show()
                        BaseApplication.getInstance().progressOff()
                        mCompositeDisposable.clear()
                    }

                    override fun onComplete() {
                        BaseApplication.getInstance().progressOff()
                    }
                })
    }
}