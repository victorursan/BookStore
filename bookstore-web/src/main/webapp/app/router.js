import Ember from 'ember';
import config from './config/environment';

const Router = Ember.Router.extend({
  location: config.locationType
});

Router.map(function() {
  this.route('authors', function() {
    this.route('newauthor');
  });
  this.route('books', function() {
    this.route('newbook');
  });
  this.route('authordetails', {path: '/authordetails/:author_id'});
  this.route('bookdetails', {path: '/bookdetails/:book_id'});
  
});

export default Router;
