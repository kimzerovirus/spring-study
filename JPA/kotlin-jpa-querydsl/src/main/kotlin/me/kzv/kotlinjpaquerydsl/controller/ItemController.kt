package me.kzv.kotlinjpaquerydsl.controller

import me.kzv.kotlinjpaquerydsl.controller.dto.BookForm
import me.kzv.kotlinjpaquerydsl.entity.items.Book
import me.kzv.kotlinjpaquerydsl.service.ItemService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping

@Controller
class ItemController(
    private val itemService: ItemService,
) {
    @GetMapping("/items/new")
    fun createForm(model: Model): String {
        model.addAttribute("form", BookForm(id = 0, name = "", author = "", isbn = "", stockQuantity = 0, price = 0))

        return "items/createItemForm"
    }

    @PostMapping("/items/new")
    fun create(form: BookForm): String {

        val book = Book(
            name = form.name,
            price = form.price,
            stockQuantity = form.stockQuantity,
            author = form.author,
            isbn = form.isbn,
        )

        itemService.saveItem(book)

        return "redirect:/"
    }

    @GetMapping("/items")
    fun list(model: Model): String {
        val items = itemService.findItems()
        model.addAttribute("items", items)

        return "items/itemList"
    }

    // ui에 뿌려줄 데이터
    @GetMapping("/items/{itemId}/edit")
    fun updateItemForm(@PathVariable("itemId") itemId: Long, model: Model): String {
        val item = itemService.findOne(itemId) as Book // type casting

        val form = BookForm(
            id = item.id!!,
            name = item.name,
            price = item.price,
            stockQuantity = item.stockQuantity,
            author = item.author,
            isbn = item.isbn
        )
        model.addAttribute("form", form)

        return "items/updateItemForm"
    }

    @PostMapping("items/{itemId}/edit")
    fun updateItem(@PathVariable("itemId") itemId: Long, @ModelAttribute("form") form: BookForm): String {
        // merge
        /*
            val book = Book(
                id = form.id,
                price = form.price,
                stockQuantity = form.stockQuantity,
                name = form.name,
                author = form.author,
                isbn = form.isbn
            )
            itemService.saveItem(book)
        */

        // dirty checking - 변경 된 속성만 바꾼다.
        itemService.updateItem(itemId, form.name, form.price, form.stockQuantity)

        return "redirect:/items"
    }
}