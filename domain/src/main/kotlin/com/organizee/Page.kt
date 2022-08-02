package com.organizee

class Page<T>(
    val itens: List<T>,
    val totalPages: Int,
    val count: Long,
    val currentPage: Int,
    val nextPage: Int,
) {
    companion object {
        fun <I> of(page: org.springframework.data.domain.Page<I>) = Page(
            itens = page.toList(),
            totalPages = page.totalPages,
            count = page.totalElements,
            currentPage = page.number,
            nextPage = if (page.totalPages > page.number + 1) page.number + 1 else page.number
        )
    }
}


