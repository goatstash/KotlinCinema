import java.math.BigDecimal
import java.math.RoundingMode

fun main(args: Array<String>) {

    println("Enter the number of rows:")
    val rows = readln().toInt()

    println("Enter the number of seats in each row:")
    val seats = readln().toInt()

    var numberOfSeats = rows * seats
    var numberOfPurchasedtickets = 0

    var currentIncome = 0
    var totalIncome = 0
    fun totalIncomeCalc() {
        if (rows <= 4) {
            totalIncome = (rows * seats) * 10
        } else if (rows > 4) {
            var totalRows = rows
            totalRows -= 4
            totalIncome = ((totalRows * seats) * 8) + ((4 * seats) * 10)
        }
    }
    totalIncomeCalc()

    var s = 'S'
    var cinemaLayout = mutableListOf<MutableList<Char>>()
    val cinemaSeats = mutableListOf<Char>()

    for (x in 1..seats) {
        cinemaSeats.add(s)
    }

    for (x in 1..rows) {
        cinemaLayout.add(cinemaSeats.toMutableList())
    }

    cinemaLayout.forEachIndexed { index, row ->
        val seats = row.joinToString()
            .replace("]", "\n")
            .replace("[", "")
            .replace(",", "")
    }

    fun buyATicket() {
        println("Enter a row number:")
        val selectedRow = readln().toInt()
        if (selectedRow > rows) {
            println("Wrong input!")
            return buyATicket()
        }

        println("Enter a seat number in that row:")
        val selectedSeat = readln().toInt()

        if (selectedSeat > seats) {
            println("Wrong input!")
            return buyATicket()
        }
        var indexRow = selectedRow - 1
        var indexSeat = selectedSeat - 1

        if (selectedRow <= 4) {
            if (cinemaLayout[indexRow][indexSeat] == 'B') {
                println("That ticket has already been purchased!")
                return buyATicket()
            } else {
                cinemaLayout[indexRow][indexSeat] = 'B'
                println("Ticket price: $10")
                currentIncome += 10
                numberOfPurchasedtickets += 1
            }

        } else {
            if (cinemaLayout[indexRow][indexSeat] == 'B') {
                println("That ticket has already been purchased!")
                return buyATicket()
            } else {
                cinemaLayout[indexRow][indexSeat] = 'B'
                println("Ticket price: $8")
                currentIncome += 8
                numberOfPurchasedtickets += 1
            }
        }
    }
    fun purchasedTickets() {
        println(
            "Number of purchased tickets: ${numberOfPurchasedtickets}\n" +
                    "Percentage: ${
                        BigDecimal((numberOfPurchasedtickets.toDouble() / numberOfSeats) * 100).setScale(
                            2,
                            RoundingMode.HALF_UP
                        )
                    }%\n" +
                    "Current income: \$${currentIncome}\n" +
                    "Total income: \$${totalIncome}"
        )
    }

    do {
        println()
        println(
            "1. Show the seats\n" +
                    "2. Buy a ticket\n" +
                    "3. Statistics\n" +
                    "0. Exit"
        )
        var userSelection = readln().toInt()

        when (userSelection) {
            1 -> {
                println("Cinema:")
                print(" ")
                for (x in 1..seats) {
                    print(" ")
                    print(x)
                }
                println()
                cinemaLayout.forEachIndexed { index, row ->
                    val seats = row.joinToString()
                        .replace("]", "\n")
                        .replace("[", "")
                        .replace(",", "")
                    println("${index + 1} $seats")
                }
            }
            2 -> {
                buyATicket()
            }
            3 -> purchasedTickets()
        }
    } while (userSelection !== 0)
}
