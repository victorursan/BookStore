package com.BookStore

import com.caucho.hessian.client.HessianProxyFactory

/**
  * Created by victor on 4/20/16.
  */

object Main extends App {
  val url = "http://localhost:8080/hessian/books"
  val factory: HessianProxyFactory = new HessianProxyFactory
  val controllerService = factory.create(url).asInstanceOf[ControllerService]

  override def main(args: Array[String]) {
    println("books(): " + controllerService.getAllBooks)
  }

}
