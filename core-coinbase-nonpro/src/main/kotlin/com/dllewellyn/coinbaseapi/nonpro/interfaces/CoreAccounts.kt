package com.dllewellyn.coinbaseapi.nonpro.interfaces

import com.dllewellyn.coinbaseapi.models.account.Account
import com.dllewellyn.denarii.repositories.DeleteRepository
import com.dllewellyn.denarii.repositories.ReadOnlyRepositoryNoArguments
import com.dllewellyn.denarii.repositories.UpdateRepository

interface CoreAccounts : ReadOnlyRepositoryNoArguments<List<Account>>,
    DeleteRepository<Account>, UpdateRepository<Account, String>