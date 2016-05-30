import DS from 'ember-data';

export default DS.Model.extend({
  name: DS.attr(),
  credits: DS.attr(),
  teacher: DS.attr(),
  // students: DS.hasMany('student')
  studentDisciplines: DS.hasMany('studentDiscipline')
});
