package com.dllewellyn.coinbaseapi.nonpro.interfaces

import com.dllewellyn.coinbaseapi.models.AccountData
import com.dllewellyn.coinbaseapi.models.ApiTransaction
import com.dllewellyn.denarii.repositories.ReadOnlyRepositoryArgument

interface Transactions : ReadOnlyRepositoryArgument<AccountData, List<ApiTransaction>>