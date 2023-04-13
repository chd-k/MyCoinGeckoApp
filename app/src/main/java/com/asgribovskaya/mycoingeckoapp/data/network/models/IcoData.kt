package com.asgribovskaya.mycoingeckoapp.data.network.models


import com.google.gson.annotations.SerializedName

data class IcoData(
    @SerializedName("ico_start_date")
    val icoStartDate: String,

    @SerializedName("ico_end_date")
    val icoEndDate: String,

    @SerializedName("short_desc")
    val shortDesc: String,

    @SerializedName("description")
    val description: Any?,

    @SerializedName("links")
    val links: LinksX,

    @SerializedName("softcap_currency")
    val softcapCurrency: String,

    @SerializedName("hardcap_currency")
    val hardcapCurrency: String,

    @SerializedName("total_raised_currency")
    val totalRaisedCurrency: String,

    @SerializedName("softcap_amount")
    val softcapAmount: Any?,

    @SerializedName("hardcap_amount")
    val hardcapAmount: Any?,

    @SerializedName("total_raised")
    val totalRaised: Any?,

    @SerializedName("quote_pre_sale_currency")
    val quotePreSaleCurrency: String,

    @SerializedName("base_pre_sale_amount")
    val basePreSaleAmount: Any?,

    @SerializedName("quote_pre_sale_amount")
    val quotePreSaleAmount: Any?,

    @SerializedName("quote_public_sale_currency")
    val quotePublicSaleCurrency: String,

    @SerializedName("base_public_sale_amount")
    val basePublicSaleAmount: Int,

    @SerializedName("quote_public_sale_amount")
    val quotePublicSaleAmount: Double,

    @SerializedName("accepting_currencies")
    val acceptingCurrencies: String,

    @SerializedName("country_origin")
    val countryOrigin: String,

    @SerializedName("pre_sale_start_date")
    val preSaleStartDate: Any?,

    @SerializedName("pre_sale_end_date")
    val preSaleEndDate: Any?,

    @SerializedName("whitelist_url")
    val whitelistUrl: String,

    @SerializedName("whitelist_start_date")
    val whitelistStartDate: Any?,

    @SerializedName("whitelist_end_date")
    val whitelistEndDate: Any?,

    @SerializedName("bounty_detail_url")
    val bountyDetailUrl: String,

    @SerializedName("amount_for_sale")
    val amountForSale: Any?,

    @SerializedName("kyc_required")
    val kycRequired: Boolean,

    @SerializedName("whitelist_available")
    val whitelistAvailable: Any?,

    @SerializedName("pre_sale_available")
    val preSaleAvailable: Any?,

    @SerializedName("pre_sale_ended")
    val preSaleEnded: Boolean
)