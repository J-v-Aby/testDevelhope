async function testDevelhope() {
    try {
        const response = await fetch('https://v2.jokeapi.dev/joke/Programming');
        const data = await response.json();

        console.log(data);

        if (data.setup && data.delivery) {
            document.getElementById('setup').textContent = data.setup;
            document.getElementById('delivery').textContent = data.delivery;
        } else {
            console.log('Proprietà non esistente!!');
            alert('Proprietà non esistente!');
        }
        document.getElementById('Button').addEventListener('click',testDevelhope)
    } catch (error) {
        console.error("vedi lato client", error);
        
    }
}
testDevelhope();
