import Ember from 'ember';

export default Ember.Controller.extend({
  actions: {

    onUpdateButtonClick(client) {

      var self = this;

      function transitionToClients() {
        console.log('transition to route...');
        self.transitionToRoute('clients');
      }

      function failure(reason) {
        console.log(reason);
      }

      console.log(client);
      client.save().then(transitionToClients).catch(failure);
    },

    onInputBlur(event) {
      var client = this.get('model.client');
      var property = event.target.name;
      var value = event.target.value;
      client.set(property, value);
    },

    onAddBookButtonClick() {
      var availableBooksElement = document.getElementById('availableBooks');
      var bookId = availableBooksElement.options[availableBooksElement.selectedIndex].value;

      var book = this.store.peekRecord('book', bookId);
      var selectedBooks = this.get('model.client.books');
      selectedBooks.pushObject(book);
    },
    
    onRemoveBookButtonClick() {
      var selectedBooksElement = document.getElementById('selectedBooks');
      var bookId = selectedBooksElement.options[selectedBooksElement.selectedIndex].value;

      var book = this.store.peekRecord('book', bookId);
      var selectedBooks = this.get('model.client.books');
      selectedBooks.removeObject(book);
    }
  }

});
