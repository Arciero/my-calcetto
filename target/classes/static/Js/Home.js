

function CreaMatch(id_container){
    // Contenitore padre
    const target_element = document.getElementById(id_container);
    // Match
    const match = document.createElement("div");
    match.className = "match";
    target_element.appendChild(match);
    // Colonna 1
    const match_column1 = document.createElement("div");
    match_column1.className = "match_column1";
        //Foto Campo
        const foto_campo = document.createElement("img");
        foto_campo.className = "campo_img";
        foto_campo.src = "campoa.jpg";
        match_column1.appendChild(foto_campo);

    match.appendChild(match_column1);

    // Colonna 2
    const match_column2 = document.createElement("div");
    match_column2.className = "match_column2";
    match.appendChild(match_column2);
        // Organizzatore
        const organizzatore = document.createElement("div");
        organizzatore.className = "nome_organizzatore";
            var testo = document.createTextNode("Organizzatore: ");
            organizzatore.appendChild(testo);

            const nome = document.createElement("strong");
                var testo = document.createTextNode("Francesco Totti");
                nome.appendChild(testo);
            organizzatore.appendChild(nome);   

        match_column2.appendChild(organizzatore);
        //Campo
        const campo = document.createElement("div");
        campo.className ="campo"; 
            var testo = document.createTextNode("Campo 2"+" "+"("+"indoor"+")");
            campo.appendChild(testo);
        match_column2.appendChild(campo);
        //Data Ora
        const data_orario= document.createElement("div");
        data_orario.className = "data_orario";

            const icona_calendario = document.createElement("img");
            icona_calendario.className="small_icon";
            icona_calendario.src="calendar.png";
            data_orario.appendChild(icona_calendario);

            var testo = document.createTextNode(" "+"29/10/2021"+" ");
            data_orario.appendChild(testo);

            const icona_orologio = document.createElement("img");
            icona_orologio.className="small_icon";
            icona_orologio.src="orologio.png";
            data_orario.appendChild(icona_orologio);

            var testo = document.createTextNode(" "+"18:45");
            data_orario.appendChild(testo);
            
        match_column2.appendChild(data_orario);
        //Prezzo
        const prezzo = document.createElement("div");
        prezzo.className = "prezzo";
            var testo = document.createTextNode("6"+"€");
            prezzo.appendChild(testo);
        match_column2.appendChild(prezzo)

    //Colonna 3
    const match_column3 = document.createElement("div");
    match_column3.className = "match_column3";
        // NUmero Giocatori
        const numero_giocatori = document.createElement("div");
        numero_giocatori.className = "numeriGiocatori";
            var testo = document.createTextNode("7"+"/"+"10");
            numero_giocatori.appendChild(testo);
        match_column3.appendChild(numero_giocatori);
        // Tasto Partecipa
        const button_partecipa = document.createElement("button");
            var testo = document.createTextNode("Partecipa");
            button_partecipa.appendChild(testo);
        match_column3.appendChild(button_partecipa);
        
    match.appendChild(match_column3);
}

function menuEvidence(id_Element){
    var element_not_selected = document.getElementById(id_Element);
    var element_selected = document.getElementsByClassName("selected");
 
    if( element_not_selected.className === "not_selected" || ""){
        element_selected[0].className = "not_selected";
        element_not_selected.className = "selected";   
    }

    switch(id_Element) {
        case "partite_tab":
            document.getElementById("partite_organizzate").style.display = "none";
            document.getElementById("storico_partite").style.display = "none";
            document.getElementById("partite").style.display = "block";
        break;
        case "partite_organizzate_tab":
            document.getElementById("partite").style.display = "none";
            document.getElementById("storico_partite").style.display = "none";
            document.getElementById("partite_organizzate").style.display = "block";
          break;
          case "storico_partite_tab":
            document.getElementById("partite").style.display = "none";
            document.getElementById("partite_organizzate").style.display = "none";
            document.getElementById("storico_partite").style.display = "block";
          break;
        default:
          alert("WTF?!")
      }
}

function getSchedule(){
    let date = document.getElementById("inputDate").value;
    let arenaId = document.getElementById("arenaId").value;
    const requestOption = {
        method: 'GET',
        headers:{
            'Content-type': 'application/json'
        }
    }
    fetch('http://localhost:8080/api/v2/matches/date/'+date+'/'+arenaId, requestOption)
    .then(res => res.json())
    .then(data => new Map(Object.entries(data)))
    .then(map => createSchedulerButtons(map))
}
function postCreateMatch(){
    let isPrivate = document.querySelector('input[name="isPrivate"]:checked').value;
    let date = document.getElementById("inputDate").value;
    let time = document.querySelector('input[name="scheduleRadio"]:checked').value;
    let arenaId = document.getElementById("arenaId").value;

    let createMatchForm = {
        isPrivate: isPrivate,
        dateTime: date+'T'+time,
        idArena: arenaId
    }

    const requestOption = {
        method: 'POST',
        headers:{
            'Content-type': 'application/json'
        },
        body: JSON.stringify(createMatchForm)

    }
    fetch('http://localhost:8080/api/v2/matches/creatematch', requestOption)
    .then(res => res.json())
    .then(data => {
        alert(data.message);
        location.reload();
    })

}

function createSchedulerButtons(map){
    let lable = `<label>Fascia oraria</label><br>`;
    let startTime = 10;
    let lastTime = 23;
    let buttons = "";
    while(startTime <= lastTime){
        let code = `<input label="${startTime}:00" type="radio" name = "scheduleRadio" value="${startTime}:00" ${map.get(''+startTime) ? "disabled" : ""}>`;
        buttons += code;
        startTime++;
    }
    let submit = `<br><br><button onclick="postCreateMatch()">Conferma</button>`;
    document.getElementById("schedule").innerHTML = lable+buttons+submit;
}

function changeVisibility(id_Element){
    let targetElement= document.getElementById(id_Element);
    let visibleElement = document.getElementsByClassName("visible");
    if(!targetElement.isEqualNode(visibleElement[0])){
        targetElement.classList.replace("hide","visible")
        visibleElement[0].classList.replace("visible","hide");
    }
}

function resetSchedule(){
    document.getElementById('schedule').innerHTML = "";

}