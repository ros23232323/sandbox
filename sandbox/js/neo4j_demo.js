var neo4j = require('neo4j');
var db = new neo4j.GraphDatabase({
    url: 'http://localhost:7474',
    auth: {username: 'neo4j', password: 'Lecarr0w'}
});


function create_callback(err, results) {
    if (err) throw err;
    console.log(JSON.stringify(results));
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

db.cypher({
    query: 'MERGE (p:Person { name:{name}, role:{}, profile_url:{profile_url}}) RETURN p',
    params: {
        name: 'Colette McDermott',
        age: 33
    },
}, create_callback);

db.cypher({
    query: 'MATCH (user:Person {age: {age}}) RETURN user',
    params: {
        age: 33,
    },
}, query_callback);
