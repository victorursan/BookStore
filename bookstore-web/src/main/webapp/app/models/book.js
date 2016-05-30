import DS from 'ember-data';

export default DS.Model.extend({
  title:      DS.attr('string'),
  author:     DS.attr('string'),
  isbn:       DS.attr('number'),
  genre:      DS.attr('string'),
  publisher:  DS.attr('string'),
  price:      DS.attr('number'),
  available:  DS.attr('boolean')
});
