//package com.BookStore
//
//import com.BookStore.ControllerService
//import com.caucho.hessian.client.HessianProxyFactory
//import com.BookStore.ui.Console
//
///**
//  * Created by victor on 4/20/16.
//  */
//
//object Main extends App {
//  override def main(args: Array[String]) {
//    val url = "http://localhost:8080/hessian/books"
//    val factory: HessianProxyFactory = new HessianProxyFactory
//    val controllerService = factory.create(url).asInstanceOf[ControllerService]
//    new Console(controllerService).run()
//  }
//
//}
