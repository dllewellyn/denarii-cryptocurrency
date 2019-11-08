package com.dllewellyn.coinbaseapi.nonpro.interfaces

import com.dllewellyn.coinbaseapi.models.AccountData
import com.dllewellyn.coinbaseapi.models.AddressApi
import com.dllewellyn.denarii.repositories.ReadOnlyRepositoryArgument

interface Addresses : ReadOnlyRepositoryArgument<AccountData, List<AddressApi>>