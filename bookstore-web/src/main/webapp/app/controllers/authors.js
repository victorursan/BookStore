import Ember from 'ember';

export default Ember.Controller.extend({
  actions: {

    deleteAuthor(authorId){
      console.log("deleteAuthor ", authorId);

      var author = this.store.peekRecord('author', authorId);
      this.store.deleteRecord(author);
      author.save();

      this.transitionToRoute('authors');
    }

  }
});
