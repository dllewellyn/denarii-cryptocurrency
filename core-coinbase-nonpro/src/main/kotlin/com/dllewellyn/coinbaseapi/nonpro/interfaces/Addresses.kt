package com.dllewellyn.coinbaseapi.nonpro.interfaces

import com.dllewellyn.coinbaseapi.models.AccountData
import com.dllewellyn.coinbaseapi.models.AddressApi
import com.dllewellyn.coinbaseapi.models.CreateAddressApi
import com.dllewellyn.denarii.repositories.ReadOnlyRepositoryArgument
import com.dllewellyn.denarii.repositories.WriteRepositoryArgument

interface Addresses : ReadOnlyRepositoryArgument<AccountData, List<AddressApi>>,
    WriteRepositoryArgument<AccountData, CreateAddressApi>