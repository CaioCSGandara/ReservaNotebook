import {useEffect} from "react";
import fetchGetAll from "../apiService.js";

function Tabela() {

    useEffect(() => {
        fetchGetAll();
    })

    return(
        <>
        <table>
            <thead>
                <tr>
                    <th>Nome</th>   
                    <th>RA</th>
                    <th>E-mail</th>
                    <th>Telefone</th>
                    <th>Curso</th>
                    <th>Uso</th>
                    <th>Patrimonio Notebook</th>
                </tr>
            </thead>
        </table>
        </>)
}

export default Tabela;