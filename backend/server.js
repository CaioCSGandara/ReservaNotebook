import express from 'express';
import mongo from './database/mongo.js';

const app = express();

app.get('/', async (req, res) => {

    try {

        let data = await mongo.getAllReservas();

        if(data.length==0) {
            return res.status(204).send();
        }

        res.status(200).send(data);
    }
    catch(error) {
        console.log("Erro no servidor: " + error);
        res.status(500).send("Ocorreu um erro no servidor. Tente novamente mais tarde.");
    }
});

app.use(express.json());

app.post('/', async (req, res) => {

    try {
        let reserva = req.body;

        console.log(req.body);

        await mongo.addReserva(reserva); //validação será feita na interface

        res.status(201).send();
    }
    catch(error) {
        console.log("Erro no servidor: " + error);
        res.status(500).send("Ocorreu um erro no servidor. Tente novamente mais tarde.");
    }
})



app.listen(8000, () => {
    console.log("Server running on port 8000.")
});