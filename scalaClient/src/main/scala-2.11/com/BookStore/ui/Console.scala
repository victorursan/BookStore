package com.BookStore.ui

import com.BookStore.ControllerService
import com.BookStore.Models.{Book, Client}

import scala.collection.JavaConverters._
import scala.annotation.tailrec
import scala.io.StdIn

/**
  * Created by victor on 4/21/16.
  */
class Console(controller: ControllerService) {

  def run(): Unit = allOptions()

  @tailrec
  private def readString(message: String): String = {
    print(message)
    try
      StdIn.readLine
    catch {
      case e: Exception => readString(message)
    }
  }

  @tailrec
  private def readInteger(message: String): Int = {
    print(message)
    try
      StdIn.readInt
    catch {
      case e: Exception => readInteger(message)
    }
  }

  @tailrec
  private def readLong(message: String): Long = {
    print(message)
    try
      StdIn.readLong
    catch {
      case e: Exception => readLong(message)
    }
  }

  @tailrec
  private def readBool(message: String): Boolean = {
    print(message)
    try
      StdIn.readBoolean
    catch {
      case e: Exception => readBool(message)
    }
  }

  private def printEntityList[E](elements: List[E]) = elements foreach println

  private def makeCollection[E](iterable: Iterable[E]): List[E] = iterable.toList

  private def addClient(): Unit = {
    val firstName: String = readString("Enter first name: ")
    val lastName: String = readString("Enter last name:")
    controller.addClient(firstName, lastName)
  }

  private def addBook(): Unit = {
    val title: String = readString("Enter title: ")
    val auth: String = readString("Enter author:")
    val isbn: Long = readLong("Enter ISBN: ")
    val genre: String = readString("Enter genre: ")
    val publisher: String = readString("Enter publisher: ")
    val price: Int = readInteger("Enter price: ")
    controller.addBook(title, auth, isbn, genre, publisher, price)
  }

  private def updateClient(): Unit = {
    val id: Integer = readInteger("Id of client to update: ")
    val firstName: String = readString("Enter first name: ")
    val lastName: String = readString("Enter last name:")
    controller.updateClient(id, firstName, lastName)
  }

  private def updateBook(): Unit = {
    val id: Integer = readInteger("Id of book to update: ")
    val title: String = readString("Enter title: ")
    val auth: String = readString("Enter author:")
    val isbn: Long = readLong("Enter ISBN: ")
    val genre: String = readString("Enter genre: ")
    val publisher: String = readString("Enter publisher: ")
    val price: Integer = readInteger("Enter price: ")
    val available: Boolean = readBool("Is Available (true/ false):")
    controller.updateBook(id, title, auth, isbn, genre, publisher, price, available)
  }

  private def deleteClient(): Unit = {
    printEntityList(makeCollection[Client](controller.getAllClients.asScala))
    val id: Integer = readInteger("Id of client to remove: ")
    controller.deleteClient(id)
  }

  private def deleteBook(): Unit = {
    printEntityList(makeCollection[Book](controller.getAllBooks.asScala))
    val id: Integer = readInteger("Id of book to remove: ")
    controller.deleteBook(id)
  }

  private def clientBooks(): Unit = {
    printEntityList(makeCollection[Client](controller.getAllClients.asScala))
    val id: Integer = readInteger("Display books for client: ")
    printEntityList(controller.clientBooks(id).asScala.toList)
  }

  private def clientReturnMode(): Unit = {
    printEntityList(makeCollection[Client](controller.getAllClients.asScala))
    val client: Integer = readInteger("Which client wants to return?")
    printEntityList(controller.clientBooks(client).asScala.toList)
    val book: Integer = readInteger("Which book is wanted for return?")
    controller.returnBook(client, book)
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

  private def expensiveBooks(): Unit = {
    val price: Integer = readInteger("Books more expensive than: ")
    printEntityList(controller.filterBooksMoreExpensiveThan(price).asScala.toList)
  }

  private def cheaperBooks(): Unit = {
    val price: Integer = readInteger("Books cheaper than: ")
    printEntityList(controller.filterBooksCheaperThan(price).asScala.toList)
  }

  private def authorBooks(): Unit = {
    val auth: String = readString("Enter author:")
    printEntityList(controller.filterBooksByAuthor(auth).asScala.toList)
  }

  private def genreBooks(): Unit = {
    val genre: String = readString("Enter genre:")
    printEntityList(controller.filterBooksByGenre(genre).asScala.toList)
  }

  private def showAvailableBooks(): Unit = printEntityList(controller.availableBooks.asScala.toList)

  private def showAllBooks(): Unit = printEntityList(makeCollection[Book](controller.getAllBooks.asScala))

  private def showAllClients(): Unit = printEntityList(makeCollection[Client](controller.getAllClients.asScala))

  @tailrec
  private def allOptions(): Unit = {
    println(controller.getAllOptions)
    val option: Int = readInteger("Option: ")
    option match {
      case 1 => addClient()
      case 2 => addBook()
      case 3 => deleteClient()
      case 4 => deleteBook()
      case 5 => updateClient()
      case 6 => updateBook()
      case 7 => showAllClients()
      case 8 => showAllBooks()
      case 9 => showAvailableBooks()
      case 10 => genreBooks()
      case 11 => authorBooks()
      case 12 => cheaperBooks()
      case 13 => expensiveBooks()
      case 14 => mostBooksClient()
      case 15 => mostMoneyClient()
      case 16 => clientBuyMode()
      case 17 => clientReturnMode()
      case 18 => clientBooks()
      case 0 => return
      case _ => println("Invalid option, try again.")
    }
    allOptions()
  }
}
