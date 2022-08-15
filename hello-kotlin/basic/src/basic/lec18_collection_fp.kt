package basic

/**
 * 1. filter & map
 * 2. 다양한 컬렉션 처리 기능
 * 3. List 를 Map 으로
 * 4. 중첩된 컬렉션 처리
 */


fun main() {
    val fruits = listOf(
        KotlinFruit(1, "사과", 2000L, 10000L),
        KotlinFruit(2, "사과", 1200L, 17000L),
        KotlinFruit(3, "포도", 2000L, 3000L),
        KotlinFruit(4, "바나나", 2000L, 15000L),
        KotlinFruit(5, "사과", 3600L, 35000L),
    )

    val apples = fruits.filter { fruit -> fruit.name == "사과" }
    val apples2 = fruits.filterIndexed { idx, fruit ->
        println(idx)
        fruit.name == "사과"
    }
    val applePrices = fruits.filter { fruit -> fruit.name == "사과" }
        .map { fruit -> fruit.currentPrice }
    val applePrices2 = fruits.filter { fruit -> fruit.name == "사과" }
        .mapIndexed { idx, fruit ->
            println(idx)
            fruit.currentPrice
        }

    // all : 조건을 모두 만족하면 true 그렇지 않다면 false
    val isAllApple = fruits.all { fruit -> fruit.name == "사과" }

    // none : 조건을 모두 불만족하면 true 그렇지 않다면 false
    val isNotApple = fruits.none { fruit -> fruit.name == "사과" }

    // any : 조건을 하나라도 만족하면 true 그렇지 않다면 false
    val isAnyApple = fruits.any { fruit -> fruit.factoryPrice >= 10000 }

    val fruitCount = fruits.count() // size 와 같음

    val fruitSorted = fruits.sortedBy { fruit -> fruit.currentPrice } // 오름 차순
    val fruitSortedDescending = fruits.sortedByDescending { fruit -> fruit.currentPrice } // 내림차순

    val distinctFruitNames = fruits.distinctBy { fruit -> fruit.name }.map { fruit -> fruit.name } // 변형된 값을 기준으로 중복 제거

    fruits.first() // 첫번째 값을 가져옴 (무조건 null 이 아니어야함 -> Exception 남)
    fruits.firstOrNull() // 첫번째 값 또는 null
    fruits.last()
    fruits.lastOrNull()


    // 과일 이름으로 그룹
    val map: Map<String, List<KotlinFruit>> = fruits.groupBy { fruit -> fruit.name }
    println(map)

    // value 쪽에 리스트가 아니라 단일 객체가 들어간다.
    // 아이디 즉, 중복되지 않는 키를 만들때? 사용
    val map2: Map<Long, KotlinFruit> = fruits.associateBy { fruit -> fruit.id }
    println(map2)

    // 키 = 과일이름 값 = 출고가
    val map3: Map<String, List<Long>> = fruits.groupBy({ fruit -> fruit.name }, { fruit -> fruit.factoryPrice })

    // id to 출고가
    val map4: Map<Long, Long> = fruits.associateBy({ fruit -> fruit.id }, { fruit -> fruit.factoryPrice })


    /**
     * 중첩 리스트
     */
    val fruit2D: List<List<KotlinFruit>> = listOf(
        listOf(
            KotlinFruit(1, "사과", 2000L, 10000L),
            KotlinFruit(2, "사과", 1200L, 17000L),
            KotlinFruit(5, "사과", 3600L, 35000L),
        ),
        listOf(
            KotlinFruit(3, "포도", 2000L, 3000L),
            KotlinFruit(6, "포도", 2800L, 2800L),
        ),
        listOf(
            KotlinFruit(4, "바나나", 2000L, 15000L),
            KotlinFruit(7, "바나나", 5000L, 35000L),
        )
    )

    // 출고가와 현재가가 동일한 과일
    // flatMap 이중리스트를 단일 리스트로 만들어주는데 이 때 조건을 람다로 걸 수 있다.
    val samePriceFruits = fruit2D.flatMap { list ->
        list.filter { fruit -> fruit.factoryPrice == fruit.currentPrice }
    }
    println(samePriceFruits)

    // 확장함수를 이용한 표기
    val samePriceFruit2 = fruit2D.flatMap { list -> list.samePriceFilter }

    // List<List<Fruit>> -> List<Fruit>
    val flatten: List<KotlinFruit> = fruit2D.flatten()
    println(flatten)


//    val values = fruits.filter {fruit -> fruit.name == "사과"}
//        .mapNotNull { fruit -> fruit.nullOrValue() }
}

data class KotlinFruit(
    val id: Long,
    val name: String,
    val factoryPrice: Long,
    val currentPrice: Long
) {
    val isSamePrice: Boolean
        get() = factoryPrice == currentPrice
}

val List<KotlinFruit>.samePriceFilter: List<KotlinFruit>
    get() = this.filter(KotlinFruit::isSamePrice)