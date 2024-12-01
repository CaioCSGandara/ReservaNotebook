import express from 'express';
import mongo from './database/mongo.js';

const app = express();

app.get('/', (req, res) => {
    res.send("Server running on port 8000.");
});


app.listen(8000, () => {
    console.log("Server running on port 8000.")
});


let reserva = {
    "nome": "Maria",
    "ra": "927364810",
    "email": "maria@universidade.com",
    "telefone": "1983726451",
    "curso": "Engenharia",
    "uso": "Pesquisa",
    "patrimonio": "98123746"
  }
  

//await mongo.addReserva(reserva);

//await mongo.alterReserva("927364810", "email", "maria@puc.com");

await mongo.deleteReserva("927364810");

console.log(await mongo.getAllReservas());