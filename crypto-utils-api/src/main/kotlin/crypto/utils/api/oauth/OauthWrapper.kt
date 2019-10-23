package crypto.utils.api.oauth

import com.dllewellyn.coinbaseapi.models.OauthProvider

data class OauthWrapper(val user : String, val oauth : OauthProvider)