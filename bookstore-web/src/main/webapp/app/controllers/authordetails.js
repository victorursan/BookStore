import Ember from 'ember';

export default Ember.Controller.extend({
  actions: {

    onUpdateButtonClick(author) {

      var self = this;

      function transitionToAuthors() {
        console.log('transition to route...');
        self.transitionToRoute('authors');
      }

      function failure(reason) {
        console.log(reason);
      }

      console.log(author);
      author.save().then(transitionToAuthors).catch(failure);
    },

    onInputBlur(event) {
      var author = this.get('model.author');
      var property = event.target.name;
      var value = event.target.value;
      author.set(property, value);
    },


    onRemoveBookButtonClick() {
      var selectedBooksElement = document.getElementById('selectedBooks');
      var bookId = selectedBooksElement.options[selectedBooksElement.selectedIndex].value;

      var book = this.store.peekRecord('book', bookId);
      var selectedBooks = this.get('model.author.books');
      selectedBooks.removeObject(book);
    }
  }

});
