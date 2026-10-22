
var monElementAudio = document.querySelectorAll("audio");
var audio           = document.querySelector("audio");
var boutonz         = document.getElementById("boutons");
var surprise        = document.getElementById("haha");
var tpt;tpt = document.getElementById("tpt");
//var idMusique       = document.getElementById("musique");
//console.log(monElementAudio);
let ind=1;
/*for(e of monElementAudio)
{
  e.volume = 0.25;
  e.setAttribute("class", "mint");
}*/

monElementAudio.forEach((audio) =>
{
  audio.volume = 0.27093;
  audio.setAttribute("class", "mint");
    audio.addEventListener("play", () =>
    {
        monElementAudio.forEach((autreAudio) =>
        {
            if (autreAudio !== audio)
            {
                autreAudio.pause();
            }
        });
    });
});

function getRandomInt(max) {
  return Math.floor(Math.random() * max);
}

let key = 0;
let keyBis;

btnRandom.onclick=function()
{
 keyBis = key;
 key  = getRandomInt(10);
 audio = monElementAudio[key];
 audioBis = monElementAudio[keyBis];
 audioBis.currentTime=0;
 audio.play();
 console.log("Vous écoutez actuellement la track n°" +(key+1) + " du tableau !");
}

btnStop.onclick = function()
{
  audio.pause();
  for(a of monElementAudio)
  {
    a.currentTime = 0;
  }
  document.body.removeChild(img);
  clickCount=0;
}

function show_image(src, width, height,alt, id) {

        let img = document.createElement("img");
        img.src    = src;
        img.width  = width;
        img.height = height;
        img.alt    = alt;
        img.id     = id;

        document.body.appendChild(img);
        return img;
    }


let clickCount = 0;
btnAll.onclick = function()
{
  clickCount++;
  surprise.currentTime = 0;
  audio.pause();
  surprise.play();
  url = "https://random-d.uk/api/randomimg?t=12575557048"
  rol = getRandomInt(1000);
  key  = getRandomInt(100);
  rolString= rol.toString();
  url += rolString;


  if(clickCount<2)
  {
  img = show_image("covers/nah.jpeg",
      1200, 800,"tpt", "tpt");

  }
  else
  {
    document.body.removeChild(img);
    img = show_image(url,
        1200, 800,"tpt", "tpt");
  }

  //image = show_image(url,
    //  1200, 800,"tpt", "tpt");
  old_url = url;
}
