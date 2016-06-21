import DS from 'ember-data';

export default DS.Model.extend({
  title:      DS.attr('string'),
  isbn:       DS.attr('number'),
  year:       DS.attr('number'),
  author:     DS.belongsTo('author')
});
