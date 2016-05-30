// import Model from 'ember-data/model';
import DS from 'ember-data';

export default DS.Model.extend({
  student: DS.belongsTo('student'),
  discipline: DS.belongsTo('discipline'),
  grade: DS.attr()

});
