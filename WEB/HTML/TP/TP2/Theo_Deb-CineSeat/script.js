// MAP

const rows = ["A", "B", "C", "D", "E", "F", "G"];

const seatColors = 
    [
        ["red", "red", "orange", "orange","orange", "orange", "red", "red"],

        ["orange", "orange", "yellow", "yellow","yellow", "yellow", "orange", "orange"],

        ["orange", "yellow", "yellow", "green","green", "yellow", "yellow", "orange"],

        ["yellow", "yellow", "green", "green","green", "green", "yellow", "yellow"],

        ["yellow", "green", "green", "green","green", "green", "green", "yellow"],

        ["orange", "yellow", "green", "green","green", "green", "yellow", "orange"],

        ["orange", "yellow", "yellow", "green","green", "yellow", "yellow", "orange"]
    ];

const grid = document.querySelector("#seat-grid");
const info = document.querySelector("#seat-info");

function createSeats() 
    {
        rows.forEach((row, rowIndex) => 
            {
                const label = document.createElement("span");
                label.className = "row-label";
                label.textContent = row;
                grid.appendChild(label);

                seatColors[rowIndex].forEach
                (  
                    (color, index) => 
                    {
                        if (index === 4) 
                        {
                            const aisle = document.createElement("span");
                            grid.appendChild(aisle);
                        }

                        const seat = document.createElement("button");
                        seat.className = `seat ${color}`;

                        seat.setAttribute
                        (
                            "aria-label",
                            `Rangée ${row}, siège ${index + 1}`
                        );

                        seat.addEventListener
                        (
                            "mouseover", () => {
                            selectSeat(seat, row, index + 1);}
                        );

                        grid.appendChild(seat);
                    }
                );
            }
        );
    }

function selectSeat(element, row, number) 
    {
        document.querySelectorAll(".seat.selected")
        .forEach(seat => seat.classList.remove("selected"));

        element.classList.add("selected");

        info.innerHTML = 
        `
            <small>SELECTED SEAT</small>
            <h3>Row ${row}, Seat ${number}</h3>
            <blockquote>
                “Perfect eye-level alignment, insane bass response.”
            </blockquote>
            <p style="color:#99949e;font-size:9px">
                42 ratings · Top 3% in this room
            </p>
        `;
    }

createSeats();

const initialSeat = document.querySelector
    (
        '.seat[aria-label="Rangée A, siège 1"]'
    );

if (initialSeat) 
    {
        selectSeat(initialSeat, "A", 1);
    }

// POPCORN

let logoClickCount = 0;
let clickTimeout = null;

function createPopcornShower() 
    {
        clearTimeout(clickTimeout);

        logoClickCount++;


        clickTimeout = setTimeout(() => 
            {
                logoClickCount = 0;
                console.log("Compteur réinitialisé par manque de rapidité !");
            }, 2000);

            if (logoClickCount < 5) 
                {
                    return; 
                }

        clearTimeout(clickTimeout);
        logoClickCount = 0;

        const popcornImages = 
            [
                'img/popcorn1.png',
                'img/popcorn2.png',
                'img/popcorn3.png',
                'img/popcorn4.png'
            ];

        const totalPopcorns = 120; 

        for (let i = 0; i < totalPopcorns; i++) {
            const popcorn = document.createElement('img');
            const randomImage = popcornImages[Math.floor(Math.random() * popcornImages.length)];
        
            popcorn.src = randomImage;
            popcorn.classList.add('popcorn-particle');

            const randomX = Math.random() * window.innerWidth;
            popcorn.style.left = `${randomX}px`;

            const duration = Math.random() * 1 + 0.75;
            const delay = Math.random() * 2;
            const drift = (Math.random() - 0.5) * 800;
            const rotation = (Math.random() - 0.5) * 720;

            popcorn.style.animationDuration = `${duration}s`;
            popcorn.style.animationDelay = `${delay}s`;
            popcorn.style.setProperty('--drift', `${drift}px`);
            popcorn.style.setProperty('--rotation', `${rotation}deg`);

            document.body.appendChild(popcorn);

            setTimeout(() => {
                popcorn.remove();
            }, (duration + delay) * 1000);
        }
    }
