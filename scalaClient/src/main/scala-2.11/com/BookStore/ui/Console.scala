package com.BookStore.web.ui

/**
  * Created by victor on 4/21/16.
  */
class Console(val controller: ControllerService) {
  private[this] val options: Map[Int, () => Unit] = Map(1 -> addClient, 2 -> addBook, 3 -> deleteClient, 4 -> deleteBook,
    5 -> updateClient, 6 -> updateBook, 7 -> showAllClients, 8 -> showAllBooks, 9 -> showAvailableBooks,
    10 -> genreBooks, 11 -> authorBooks, 12 -> cheaperBooks, 13 -> expensiveBooks, 14 -> mostBooksClient,
    15 -> mostMoneyClient, 16 -> clientBuyMode, 17 -> clientReturnMode, 18 -> clientBooks)

  def run(): Unit = allOptions()

  @tailrec
  private def read[T](readType: => T)(message: String): T = {
    print(message)
    try
      readType
    catch {
      case e: Exception => read(readType)(message)
    }
  }

  private val readString: String => String = read(StdIn.readLine)

  private val readInteger: String => Int = read(StdIn.readInt)

  private val readLong: String => Long = read(StdIn.readLong)

  private val readBool: String => Boolean = read(StdIn.readBoolean)

  private def printEntityList[E](elements: List[E]) = elements foreach println

  private def makeCollection[E](iterable: Iterable[E]): List[E] = iterable.toList

  private def addClient(): Unit = controller.addClient(readString("Enter first name: "),
                                                       readString("Enter last name:"))

  private def addBook(): Unit = controller.addBook(readString("Enter title: "),
                                                   readString("Enter author:"),
                                                   readLong("Enter ISBN: "),
                                                   readString("Enter genre: "),
                                                   readString("Enter publisher: "),
                                                   readInteger("Enter price: "))

  private def updateClient(): Unit = controller.updateClient(readInteger("Id of client to update: "),
                                                             readString("Enter first name: "),
                                                             readString("Enter last name:"))

  private def updateBook(): Unit = controller.updateBook(readInteger("Id of book to update: "),
                                                         readString("Enter title: "),
                                                         readString("Enter author:"),
                                                         readLong("Enter ISBN: "),
                                                         readString("Enter genre: "),
                                                         readString("Enter publisher: "),
                                                         readInteger("Enter price: "),
                                                         readBool("Is Available (true/ false):"))

  private def deleteClient(): Unit = {
    printEntityList(makeCollection[Client](controller.getAllClients.asScala))
    controller.deleteClient(readInteger("Id of client to remove: "))
  }

  private def deleteBook(): Unit = {
    printEntityList(makeCollection[Book](controller.getAllBooks.asScala))
    controller.deleteBook(readInteger("Id of book to remove: "))
  }

  private def clientBooks(): Unit = {
    printEntityList(makeCollection[Client](controller.getAllClients.asScala))
    printEntityList(controller.clientBooks(readInteger("Display books for client: ")).asScala.toList)
  }

  private def clientReturnMode(): Unit = {
    printEntityList(makeCollection[Client](controller.getAllClients.asScala))
    val client: Integer = readInteger("Which client wants to return?")
    printEntityList(controller.clientBooks(client).asScala.toList)
    controller.returnBook(client, readInteger("Which book is wanted for return?"))
  }

  private def clientBuyMode(): Unit = {
    printEntityList(makeCollection[Client](controller.getAllClients.asScala))
    val client: Integer = readInteger("Which client wants to purchase? ")
    printEntityList(controller.availableBooks.asScala.toList)
    val book: Integer = readInteger("Which book is wanted for buying? ")
    controller.buyBook(client, book)
  }

  private def mostMoneyClient(): Unit = println(controller.clientWhoSpentMost)

  private def mostBooksClient(): Unit = println(controller.clientWithMostBooks)

  private def expensiveBooks(): Unit =
    printEntityList(controller.filterBooksMoreExpensiveThan(readInteger("Books more expensive than: ")).asScala.toList)


  private def cheaperBooks(): Unit =
    printEntityList(controller.filterBooksCheaperThan(readInteger("Books cheaper than: ")).asScala.toList)


  private def authorBooks(): Unit =
    printEntityList(controller.filterBooksByAuthor(readString("Enter author:")).asScala.toList)


  private def genreBooks(): Unit =
    printEntityList(controller.filterBooksByGenre(readString("Enter genre:")).asScala.toList)


  private def showAvailableBooks(): Unit = printEntityList(controller.availableBooks.asScala.toList)

  private def showAllBooks(): Unit = printEntityList(makeCollection[Book](controller.getAllBooks.asScala))

  private def showAllClients(): Unit = printEntityList(makeCollection[Client](controller.getAllClients.asScala))

  @tailrec
  private def allOptions(): Unit = {
    println(controller.getAllOptions)
    val option: Int = readInteger("Option: ")
    if (option != 0) {
      options.getOrElse(option, () => println("Invalid option"))()
      allOptions()
    }
  }
}
