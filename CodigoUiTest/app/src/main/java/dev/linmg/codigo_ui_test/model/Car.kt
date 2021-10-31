package dev.linmg.codigo_ui_test.model

data class Car(
    val id: Int,
    val name: String,
    val type: String,
    val noOfSeat: Int,
)

val cars: List<Car> =
    listOf(
        Car(1, "Mazda 3", "SMS1000Z", 4),
        Car(2, "Honda Shuttle Hybrid", "SMN7000B", 5),
        Car(3, "Ssangyong Tivoli", "SMN124Z", 9),
        Car(4, "Toyota Sienta Hybrid", "SME#121", 2),
        Car(5, "BMW Boarer 1", "GEG-#11", 5),
        Car(6, "BMW Boarer 2", "GEG-#11", 5),
        Car(7, "BMW Boarer Pro", "GEG-#11", 5),
        Car(8, "BMW Boarer Chase", "GEG-#11", 5), Car(5, "BMW Boarer", "GEG-#11", 5),

        )
