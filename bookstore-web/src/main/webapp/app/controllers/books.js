import Ember from 'ember';

export default Ember.Controller.extend({
  actions: {

    deleteBook(bookId){
      console.log("deleteBook ", bookId);

      var book = this.store.peekRecord('book', bookId);
      this.store.deleteRecord(book);
      book.save();

      this.transitionToRoute('books');
    }

  }
});
