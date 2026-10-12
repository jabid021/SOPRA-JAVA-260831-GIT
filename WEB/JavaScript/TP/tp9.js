
let minute = 0;
let seconde=0;
let varInterval;

btnStart.onclick = function()
{
  varInterval = setInterval(timer,1000);
  btnStart.disabled=true;
  btnStop.disabled=false;
}

btnStop.onclick = function()
{
  clearInterval(varInterval);
  btnStart.disabled=false;
  btnStop.disabled=true;
}

btnReset.onclick = function()
{
  seconde=0;
  minute=0;
  affiche();
}



function timer()
{
  seconde++;
  if(seconde==60)
  {
    minute++;
    seconde=0;
    if(minute==60){minute=0;}
  }
  affiche();
}

function affiche()
{
  minuteAffiche = (minute<10)? "0"+minute : ""+minute;
  secondeAffiche = ( seconde<10)? "0"+seconde : ""+seconde;
  chrono.innerHTML=`${minuteAffiche}:${secondeAffiche}`;
}
