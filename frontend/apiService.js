async function fetchGetAll() {
   
    const url = "http://localhost:8000/listarReservas";

    try {
        const response = await fetch(url, {
            method: 'GET',
            headers: {'Content-Type': 'application/json'}});
        
        if(response.ok) {
            //  return await response.json();

            const resp = await response.json()
            
            resp.forEach(element => {
                console.log(element);
            });
        }
        
    }

    catch(erro) {
        console.log("ERRO NA REQUISIÇAO GET: " + erro);
    }
}

export default {fetchGetAll}


