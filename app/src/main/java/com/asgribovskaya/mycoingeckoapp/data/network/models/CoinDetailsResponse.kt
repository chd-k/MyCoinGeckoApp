package com.asgribovskaya.mycoingeckoapp.data.network.models


import com.google.gson.annotations.SerializedName

data class CoinDetailsResponse(
    @SerializedName("id")
    val id: String,

    @SerializedName("symbol")
    val symbol: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("asset_platform_id")
    val assetPlatformId: Any?,

    @SerializedName("platforms")
    val platforms: Platforms,

    @SerializedName("detail_platforms")
    val detailPlatforms: DetailPlatforms,

    @SerializedName("block_time_in_minutes")
    val blockTimeInMinutes: Int,

    @SerializedName("hashing_algorithm")
    val hashingAlgorithm: String,

    @SerializedName("categories")
    val categories: List<String>,

    @SerializedName("public_notice")
    val publicNotice: Any?,

    @SerializedName("additional_notices")
    val additionalNotices: List<Any>,

    @SerializedName("description")
    val description: Description,

    @SerializedName("links")
    val links: Links,

    @SerializedName("image")
    val image: Image,

    @SerializedName("country_origin")
    val countryOrigin: String,

    @SerializedName("genesis_date")
    val genesisDate: String,

    @SerializedName("sentiment_votes_up_percentage")
    val sentimentVotesUpPercentage: Double,

    @SerializedName("sentiment_votes_down_percentage")
    val sentimentVotesDownPercentage: Double,

    @SerializedName("ico_data")
    val icoData: IcoData,

    @SerializedName("market_cap_rank")
    val marketCapRank: Int,

    @SerializedName("coingecko_rank")
    val coingeckoRank: Int,

    @SerializedName("coingecko_score")
    val coingeckoScore: Double,

    @SerializedName("developer_score")
    val developerScore: Double,

    @SerializedName("community_score")
    val communityScore: Double,

    @SerializedName("liquidity_score")
    val liquidityScore: Double,

    @SerializedName("public_interest_score")
    val publicInterestScore: Double,

    @SerializedName("public_interest_stats")
    val publicInterestStats: PublicInterestStats,

    @SerializedName("status_updates")
    val statusUpdates: List<Any>,

    @SerializedName("last_updated")
    val lastUpdated: String
)