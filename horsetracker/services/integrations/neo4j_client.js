'use strict';

var neo4j = require('neo4j');
var db = new neo4j.GraphDatabase({
    url: 'http://localhost:7474',
    auth: {username: 'neo4j', password: 'Lecarr0w'}
});

function create_callback(err, results) {
    if (err) throw err;
    console.log("create response  " + JSON.stringify(results) + "\n");
};

function query_callback(err, results) {
    if (err) throw err;
    var result = results[0];
    if (!result) {
        console.log('No user found.');
    } else {
        var user = result['user'];
        console.log(user);
    }
};

module.exports = {
    create_trainer:function(properties){
        db.cypher({
            query: 'MERGE (p:Person:Trainer { name:{name},profile_url:{profile_url}}) RETURN p',
            params: properties,
        }, create_callback);
    },
    create_jockey:function(properties){
        db.cypher({
            query: 'MERGE (p:Person:Jockey { name:{name}, profile_url:{profile_url}}) RETURN p',
            params: properties,
        }, create_callback);
    },
    create_owner:function(properties){
        db.cypher({
            query: 'MERGE (p:Person:Owner { name:{name}, profile_url:{profile_url}}) RETURN p',
            params: properties,
        }, create_callback);
    },
    create_horse:function(properties){
        db.cypher({
            query: 'MERGE (h:Horse:Runner { name:{name}, age:{age}, sex:{sex}, profile_url:{profile_url}}) RETURN h',
            params: properties,
        }, create_callback);
    },
    create_sire:function(properties){
        db.cypher({
            query: 'MERGE (h:Horse:Sire { name:{name}, profile_url:{profile_url}}) RETURN h',
            params: properties,
        }, create_callback);
    },
    create_dame:function(properties){
        db.cypher({
            query: 'MERGE (h:Horse:Dame { name:{name}, profile_url:{profile_url}}) RETURN h',
            params: properties,
        }, create_callback);
    },
    create_relationship_horse_dame:function(properties){
        db.cypher({
            query: 'MATCH (dame:Horse { name:{dame_name} }),(runner:Horse { name:{runner_name} }) CREATE UNIQUE (dame)-[r:DAME_OF]->(runner)',
            params: properties,
        }, create_callback);
    },
    create_relationship_horse_sire:function(properties){
        db.cypher({
            query: 'MATCH (sire:Horse { name:{sire_name} }),(runner:Horse { name:{runner_name} }) CREATE UNIQUE (sire)-[r:SIRE_OF]->(runner)',
            params: properties,
        }, create_callback);
    },
    create_relationship_horse_trainer:function(properties){
        db.cypher({
            query: 'MATCH (trainer:Person { name:{trainer_name} }),(runner:Horse { name:{runner_name} }) CREATE (trainer)-[r:TRAINER_OF]->(runner)',
            params: properties,
        }, create_callback);
    },
    create_relationship_horse_owner:function(properties){
        db.cypher({
            query: 'MATCH (owner:Person { name:{owner_name} }),(runner:Horse { name:{runner_name} }) CREATE (owner)-[r:TRAINER_OF]->(runner)',
            params: properties,
        }, create_callback);
    }
}
