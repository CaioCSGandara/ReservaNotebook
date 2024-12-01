import mongodb from 'mongodb';
import dotenv from 'dotenv';

dotenv.config();


async function getAllReservas() {

    let client = null;

    try {

    client = new mongodb.MongoClient(process.env.MONGO_URI);

    await client.connect();

    let resp = await client.db("puc").collection("reserva").find().toArray();

    await client.close();

    return resp;

    }

    catch(error){
        
        if(client!=null) {
        client.close();
        }

        console.log(error);
    }
}

async function addReserva(reserva) {
    
    try {

        let client = new mongodb.MongoClient(process.env.MONGO_URI);
    
        await client.connect();
    
        await client.db("puc").collection("reserva").insertOne(reserva);
    
        await client.close();
    
    
        }
    
    catch(error){
        
        if(client!=null) {
        client.close();
        }
        
        console.log(error);
    }
}


async function alterReserva(ra, field, newValue) {

    let client = null;

    try {

        client = new mongodb.MongoClient(process.env.MONGO_URI);
    
        await client.connect();
    
        await client.db("puc").collection("reserva").updateOne({"ra": ra }, { $set: {[field]: newValue}});
    
        await client.close();
    
    
        }
    
        catch(error){
        
            if(client!=null) {
            client.close();
            }
            
            console.log(error);
    }
}


async function deleteReserva(ra) {

    let client = null;

    try {

        client = new mongodb.MongoClient(process.env.MONGO_URI);
    
        await client.connect();
    
        await client.db("puc").collection("reserva").deleteOne({"ra": ra});
    
        await client.close();
    
    
        }
    
        catch(error){
        
            if(client!=null) {
            client.close();
            }
            
            console.log(error);
    }
}

export default {getAllReservas, addReserva, alterReserva, deleteReserva};