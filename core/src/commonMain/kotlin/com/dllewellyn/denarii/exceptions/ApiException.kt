package com.dllewellyn.coinbaseapi.exceptions

/**
 * Thrown when there is a 400 error with a message returned.
 */
class ApiException(s: String) : Exception(s)
