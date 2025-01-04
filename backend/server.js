import express from 'express';
import mongo from './database/mongo.js';
import cors from 'cors';

const app = express();
app.use(cors());

app.get('/listarReservas', async (req, res) => {

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

app.post('/add', async (req, res) => {

    try {
        let reserva = req.body;

        await mongo.addReserva(reserva);

        res.status(201).send();
    }
    catch(error) {
        console.log("Erro no servidor: " + error);
        res.status(500).send("Ocorreu um erro no servidor. Tente novamente mais tarde.");
    }
});


app.put('/alterar/:ra/:field/:newValue', async (req, res) => {
    
    try{
        let ra = req.params.ra;
        let field = req.params.field;
        let newValue = req.params.newValue;
    
        await mongo.alterReserva(ra, field, newValue);
            //there is no need to sent a res code if the ra is not found, because
            //the ra will be taken from the interface's table (built with get requestion)

        res.status(200).send();
    }
    catch(error) {
        console.log("Erro no servidor: " + error);
        res.status(500).send("Ocorreu um erro no servidor. Tente novamente mais tarde.");
    }
});


app.delete('/deletar/:ra', async (req, res) => {

    try {
            let ra = req.params.ra;

            await mongo.deleteReserva(ra);

            //there is no need to sent a res code if the ra is not found, because
            //the ra will be taken from the interface's table (built with get requestion)

            res.status(200).send();
    }
    catch(error) {
        console.log("Erro no servidor: " + error);
        res.status(500).send("Ocorreu um erro no servidor. Tente novamente mais tarde.");
    }
});



app.listen(8000, () => {
    console.log("Server running on port 8000.")
});