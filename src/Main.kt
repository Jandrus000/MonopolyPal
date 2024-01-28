//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
abstract class Random
// The `MonopolyPal` class represents the Monopoly game manager and contains methods for managing properties,
// money, rolling dice, and printing game rules.
class MonopolyPal {

    // standard starting amount
    var wallet = 1500

    // List of properties in the Monopoly game, initially unowned
    var properties = mutableListOf(
        Property("Mediterranean Avenue"),
        Property("Baltic Avenue"),
        Property("Oriental Avenue"),
        Property("Vermont Avenue"),
        Property("Connecticut Avenue"),
        Property("States Avenue"),
        Property("St. Charles Place"),
        Property("States Avenue"),
        Property("Virginia Avenue"),
        Property("St. James Place"),
        Property("Tennessee Avenue"),
        Property("New York Avenue"),
        Property("Indiana Avenue"),
        Property("Kentucky Avenue"),
        Property("Illinois Avenue"),
        Property("Atlantic Avenue"),
        Property("Ventnor Avenue"),
        Property("Marvin Gardens"),
        Property("Pacific Avenue"),
        Property("North Carolina Avenue"),
        Property("Pennsylvania Avenue"),
        Property("Park Place"),
        Property("Boardwalk"),
        Property("Reading RR"),
        Property("Pennsylvania RR"),
        Property("B&O RR"),
        Property("Short Line"),
        Property("Water Works"),
        Property("Eletric Company")
    )

    // Method to display the properties currently owned by the player
    fun displayProperties() {
        var displayed = false
        var moreThanOne = false
        print("\n")
        for (property in properties) {
            if (property.owned == true && property.mortgaged == true) {
                println("-" + property.name+" *mortgaged*")
                displayed = true
            }
            else if(property.owned == true && property.houses > 0){
                println("-"+ property.name +" (${property.houseType()})")
            }
            else if (property.owned == true) {
                println("-" + property.name)
                displayed = true
            }
        }
        if (!displayed) {
            println("no properties")
        }

    }

    // Inner class representing a Monopoly property
    class Property(Name: String) {
        var name = Name
        var owned = false
        var mortgaged = false
        var houses = 0

        // Method to represent the type of house structure (houses or hotel)

        fun houseType(): String{
            var structure = ""
            if(houses == 1){
                structure = "1 house"
            }else if(houses==5){
                structure = "hotel"
            }else{
                structure = "${houses} houses"
            }
            return structure

        }

    }

    // method to add a property to properties owned
    fun addProperty() {
        var counter = 1
        var empty = true
        for (property in properties) {
            if (property.owned == false) {
                print("${counter}. ${property.name}")
                empty = false

            } else {
                print("*owned* ${property.name}")
            }
            if (property.mortgaged) {
                println(" *mortgaged*")
            } else {
                println()
            }
            counter = counter + 1
        }
        if (empty) {
            println("looks like you own all the properties!")
        } else {
            print("Which property would you like to buy? >")
            var input = readln().trim()
            try {
                val propertyIndex = input.toInt() - 1
                if (!properties[propertyIndex].owned) {
                    properties[propertyIndex].owned = true
                } else {
                    println("Invalid choice, you already own this property")
                }
            } catch (nfe: NumberFormatException) {
                //not int
            }

        }
    }

    // method to remove a property from owned properties
    fun removeProperty() {
        var empty = false
        var counter = 1
        for (property in properties) {
            if (property.owned == true && property.mortgaged == true) {
                println("$counter. ${property.name} *mortgaged*")
                empty = true
            }

            else if (property.owned == true && property.houses > 0) {
                println("$counter."+ property.name +" (${property.houseType()})")
                empty = true
            }else if(property.owned == true){
                println("$counter. ${property.name}")
                empty = true
            }
            counter += 1

        }
        if (!empty) {
            println("looks like you own no properties!")
        } else {
            print("Which property would you like to remove? >")
            var input = readln().trim()
            try {
                val propertyIndex = input.toInt() - 1
                if (properties[propertyIndex].owned) {
                    properties[propertyIndex].owned = false
                } else {
                    println("Invalid choice, you dont own that property")
                }
            } catch (nfe: NumberFormatException) {
                //not int
            }
        }
    }

    //method to mortgage properties
    fun mortgageProperty() {
        var counter = 1
        var empty = true
        for (property in properties) {
            if (property.owned == true && !property.mortgaged) {
                println("${counter}. ${property.name}")
                empty = false
            }

            counter = counter + 1
        }
        if (empty) {
            println("Looks like you have no properties to mortgage")
        } else {
            print("Which property would you like to mortgage? >")
            var input = readln().trim()
            try {
                val propertyIndex = input.toInt() - 1
                if (!properties[propertyIndex].mortgaged) {
                    properties[propertyIndex].mortgaged = true
                    println("You just mortgaged ${properties[propertyIndex].name}")
                } else {
                    println("Invalid choice")
                }
            } catch (nfe: NumberFormatException) {
                //not int
            }

        }
    }

    // method to un-mortgage property
    fun unmortgageProperty(){
        var counter = 1
        var empty = true
        for (property in properties) {
            if (property.owned == true && property.mortgaged) {
                println("${counter}. ${property.name}")
                empty = false
            }
            counter = counter + 1
        }
        if (empty) {
            println("Looks like you have no properties to un-mortgage")
        } else {
            print("Which property would you like to un-mortgage? >")
            val input = readln().trim()
            try {
                val propertyIndex = input.toInt() - 1
                if (properties[propertyIndex].mortgaged) {
                    properties[propertyIndex].mortgaged = false
                    println("You just un-mortgaged ${properties[propertyIndex].name}")
                } else {
                    println("Invalid choice")
                }
            } catch (nfe: NumberFormatException) {
                //not int
            }
        }
    }

    // method to build a house
    fun buildHouse(){
        var counter = 1
        var empty = true
        for (property in properties) {
            if (property.owned == true && !property.mortgaged) {
                println("${counter}. ${property.name}")
                empty = false
            }

            counter = counter + 1
        }
        if(empty){
            println("You have no properties to build houses on")
        }else{
            print("Which property would you like to build a house on? >")
            var input = readln().trim()
            try {
                val propertyIndex = input.toInt() - 1
                if (properties[propertyIndex].houses<5) {
                    properties[propertyIndex].houses += 1
                    println("After construction, you now have ${properties[propertyIndex].houseType()} on ${properties[propertyIndex].name}")

                }else{
                    println("Invalid choice")
                }
            } catch (nfe: NumberFormatException) {
                //not int
            }
        }
    }

    // method to sell house
    fun sellHouse(){
        var counter = 1
        var empty = true
        for (property in properties) {
            if (property.owned && property.houses>0) {
                println("${counter}. ${property.name} ")
                empty = false
            }

            counter = counter + 1
        }
        if(empty){
            println("You have no properties to sell houses on")
        }else{
            print("Which property would you like to sell a house on? >")
            var input = readln().trim()
            try {
                val propertyIndex = input.toInt() - 1
                if (properties[propertyIndex].houses>0) {
                    properties[propertyIndex].houses -= 1

                    println("Success! You now have ${properties[propertyIndex].houseType()} on ${properties[propertyIndex].name}")

                }else{
                    println("Invalid choice")
                }
            } catch (nfe: NumberFormatException) {
            }
        }
    }

    // method to print rules
    fun printRules(){
        var rules =
            """
Each player starts with ${'$'}1,500 in their bank. Players roll the dice, and whoever rolls the highest number goes first. On a player's turn they roll the dice and advance their piece clockwise around the board the corresponding number of squares. If a player roll doubles, they move again after completing that turn, unless if they roll three consecutive sets of doubles on one turn. Then, they have been "caught speeding" and are immediately sent to jail instead of moving the amount shown on the dice for the third roll.

A player who lands on or passes the "GO" space collects ${'$'}200 from the bank. Players who land on either Income Tax or Luxury Tax pay the indicated amount to the bank. In older editions of the game, two options were given for Income Tax: either pay a flat fee of ${'$'}200 (or ${'$'}300) or 10% of total net worth (including the current values of all the properties and buildings owned). No calculation could be made before the choice, and no latitude was given for reversing an unwise decision. In 2008, the calculation option was removed from the official rules; simultaneously, the Luxury Tax was increased from ${'$'}75 to ${'$'}100. Nothing happens when a player lands on Free Parking.

Properties can only be developed once a player owns all the properties in that color group. They then must be developed equally. A house must be built on each property of that color before a second can be built. Each property within a group must be within one house level of all the others within that group.

Chance/Community Chest
If a player lands on a Chance or Community Chest space, they take the top card from the respective deck and follow its instructions. This may include collecting or paying money to the bank or another player or moving to a different space on the board. Two types of cards that involve jail, "Go to Jail" and "Get Out of Jail Free", are explained below.

Jail
"Go to Jail" redirects here.
A player lands in jail by:

Landing on the "Go to Jail" space
Throwing three consecutive doubles in one turn
Drawing a "Go (Directly) to Jail" card from Chance or Community Chest
When a player is sent to Jail, they do not collect their ${'$'}200 salary or pass Go. They move directly to the "In Jail" part of the "In Jail/Just Visiting" space, and their turn ends. If an ordinary dice roll (not one of the above events) ends with the player's token on the Jail corner, they are "Just Visiting" and can move ahead on their next turn without penalty.

If a player is in Jail, they cannot move and must either pay a fine of ${'$'}50 to be released, use a Chance or Community Chest Get Out of Jail Free card, or roll doubles on their next turn. If a player fails to roll doubles, they lose their turn. Failing to roll doubles for three consecutive turns requires the player to either pay the ${'$'}50 fine or use a Get Out of Jail Free card, then get out of jail move ahead according to the total rolled. Players in Jail may not buy properties directly from the bank since they cannot move. This does not impede any other transaction, meaning they can: mortgage properties, sell/trade properties to other players, buy/sell houses and hotels, collect rent, and bid on property auctions. A player who rolls doubles to leave jail does not roll again; however, if the player pays the fine or uses a card to get out and then rolls doubles, they take another turn.

The odds of rolling doubles are 6 in 36 (1 in 6) in any given roll, hence the odds of rolling into jail due to three consecutive doubles are 1 in 216 (the cube of 6.)

Properties
If the player lands on an unowned property, whether street, railroad, or utility, they must buy it or put it up for auction, based on the player's choice. If they want to buy it, they pay the listed purchase price. If they decline this purchase, the property is auctioned off by the bank to the highest bidder, including the player who declined to buy.[103] If they land on a property that someone else owns and is unmortgaged, they must pay the owner a given rent if the landlord calls for the rent within a certain time (typically it must be called before the next one or two players have thrown the dice, depending on edition); the amount depends on whether the property is part of a set or its level of improvement. Players may trade properties or sell them to other players at any time in any deal that is mutually agreed upon, with the exception of buildings. Once the player owns an entire group, they can collect double rent for any unimproved properties within it.

When a player owns all the properties in a color group and none of them are mortgaged, they can start buying houses on their turn or in between any other player's turn. They pay the bank the cost listed on the property deed to place a house on the property; this must be done evenly. Therefore, a second house cannot be built on any property within a group until all of them have one house; however, you do not have to buy them in sets of two/three at a time. Although houses and hotels cannot be built on railroads or utilities, the given rent increases if a player owns more than one of either type. If there is a housing shortage (more demand for houses to be built than what remains in the bank), then a housing auction is conducted by the bank to determine who will get to purchase each house.

Mortgaging
Properties can also be mortgaged, but buildings on a monopoly must be sold before any property of that color can be mortgaged or traded. The player receives half the purchase price from the bank for each mortgaged property. This must be repaid with 10% interest to unmortgage. Houses and hotels can be sold back to the bank for half their purchase price. Players cannot collect rent on mortgaged properties and cannot give improved property away to others; however, trading mortgaged properties is allowed, but the player receiving the mortgaged property must pay the bank the mortgage price plus 10% or keep the property mortgaged by paying just the 10% amount; if the player chooses the latter, they must pay the 10% again when they pay unmortgage.

Bankruptcy
A player who cannot pay their debts is considered bankrupt and is eliminated from the game. If the bankrupt player owes the bank, they must return all of their properties over to the bank. All properties must be put up for auction (if they have any), except buildings. If the debt is owed to another player, all properties are given to that opponent, except buildings, which must be returned to the bank. The new owner must either pay off any mortgages held by the bank on the properties received or pay a fee of 10% of the mortgaged value if they choose to leave the properties mortgaged.

The winner is the player remaining after all others have gone bankrupt. In a 2-player game, if a player goes bankrupt to the other player or the bank, the game is over and there is no need for the bank to conduct the auction as the other player will have automatically won. The winning player only then needs to pay the final fees from the property transfer.

If a player runs out of money but still has assets that can be converted to cash, they can do so by selling buildings, mortgaging properties, or trading with other players. To avoid bankruptcy the player must be able to raise enough cash to pay the full amount owed.

A player cannot choose to go bankrupt; if there is no way to pay what they owe, even by returning all their buildings at a loss, mortgaging all their real estate and giving up all their cash, even knowing they are likely going bankrupt the next time, they must do so and immediately retire from the game.

Official Short Game rules
From 1936, the rules booklet included with each Monopoly set contained a short section at the end providing rules for making the game shorter, including dealing out two Title Deed cards to each player before starting the game, by setting a time limit or by ending the game after the second player goes bankrupt. A later version of the rules included this variant, along with the time limit game, in the main rules booklet, omitting the last, the second bankruptcy method, as a third short game.[104]

-Rules from Wikipedia
            """
        print(rules)
    }

    //method to add and subtract money
    fun manageMoney(){
        print("Add money by entering a number (enter a negative number to deduct money) >")
        var input = readln()
        try{
            val amount = input.toInt()
            wallet += amount
        }catch (nfe: NumberFormatException){
            println("Invalid input")
        }


    }

    // method that rolls dice
    fun rollDice(){
        var dice1 = (1..6).random()
        var dice2 = (1..6).random()
        println("You rolled: ${dice1+dice2} ($dice1 + $dice2)")
    }


}

// method that is used throughout program when answer is invalid
    fun invalid() {
        print("Invalid choice")
        Thread.sleep(1000)
    }

    // main is the menu system that call respective methods to perform program capabilities
    fun main() {
        var myMonopolyPal = MonopolyPal()
        var input = "0"
        while (input != "5") {
            println("\n----------------------------------------------------")
            println("What would you like to do?")
            print("You currently own: ")
            myMonopolyPal.displayProperties()
            println("Your wallet: $${myMonopolyPal.wallet}")
            println("1. Manage properties")
            println("2. Manage money")
            println("3. Roll dice")
            println("4. Rules")
            println("5. Quit")
            print("\nWhat is your choice? >")
            input = readln()
            input = input.trim()


            if (input == "1") {
                println()
                println("Property Management Menu")
                println("1. Add a property")
                println("2. Remove property")
                println("3. Mortgage a property")
                println("4. Un-Mortgage a property")
                println("5. Build a house")
                println("6. Sell a house")
                print("\nWhat is your choice? >")
                var propertyInput = readln()
                propertyInput = propertyInput.trim()

                if (propertyInput == "1") {
                    myMonopolyPal.addProperty()
                } else if (propertyInput == "2") {
                    myMonopolyPal.removeProperty()
                } else if (propertyInput == "3") {
                    myMonopolyPal.mortgageProperty()
                } else if (propertyInput == "4") {
                    myMonopolyPal.unmortgageProperty()
                } else if (propertyInput == "5") {
                    myMonopolyPal.buildHouse()
                } else if (propertyInput == "6") {
                    myMonopolyPal.sellHouse()
                }  else {
                    invalid()
                }
            } else if (input == "2") {
                myMonopolyPal.manageMoney()
            } else if (input == "3") {
                myMonopolyPal.rollDice()
            } else if (input == "4") {
                myMonopolyPal.printRules()
            } else if (input != "5") {
                invalid()
            }

        }
        println("Goodbye!")


    }
