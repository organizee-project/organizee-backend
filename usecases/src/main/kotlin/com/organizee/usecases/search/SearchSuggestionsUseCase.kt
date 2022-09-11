package com.organizee.usecases.search

import com.organizee.Usecase

interface SearchSuggestionsUseCase : Usecase<String, List<String>> {}