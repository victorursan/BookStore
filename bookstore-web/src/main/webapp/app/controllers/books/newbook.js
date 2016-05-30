import Ember from 'ember';

export default Ember.Controller.extend({
  actions: {

    onAddButtonClick() {

      var self = this;

      function transitionToBooks() {
        self.transitionToRoute('books');
      }

      function failure(reason) {
        console.log(reason);
      }

      var title =     this.get('title');
      var author =    this.get('author');
      var isbn =      this.get('isbn');
      var genre =     this.get('genre');
      var publisher = this.get('publisher');
      var price =     this.get('price');
      // var available = this.get('available');

      var book = this.store.createRecord('book', {
        title: title,
        author: author,
        isbn: isbn,
        genre: genre,
        publisher: publisher,
        price: price,
        available: true
      });

      book.save().then(transitionToBooks).catch(failure);

      this.setProperties({
        title: '',
        author: '',
        isbn: '',
        genre: '',
        publisher: '',
        price: '',
        available: true
      });

    }
  }

});
