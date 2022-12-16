package me.kzv.kotlinjpaquerydsl.service

import jakarta.persistence.EntityNotFoundException
import me.kzv.kotlinjpaquerydsl.entity.items.Item
import me.kzv.kotlinjpaquerydsl.repository.ItemRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ItemService(
    private val itemRepository: ItemRepository,
) {
    @Transactional
    fun saveItem(item: Item) {
        itemRepository.save(item)
    }

    fun findItems(): List<Item> = itemRepository.findAll()

    fun findOne(itemId: Long): Item = itemRepository.findByIdOrNull(itemId) ?: throw EntityNotFoundException("id - $itemId (은)는 존재하지 않습니다.")

    @Transactional
    fun updateItem(itemId: Long, name: String, price: Int, stockQuantity: Int) {
        val findItem = itemRepository.findByIdOrNull(itemId) ?: throw EntityNotFoundException("id - $itemId (은)는 존재하지 않습니다.")
        findItem.price = price
        findItem.name = name
        findItem.stockQuantity = stockQuantity
    }
}