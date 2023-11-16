import pyttsx3
import datetime
import speech_recognition as sr
import wikipedia
import webbrowser
import os
import random
import requests
import smtplib

engine=pyttsx3.init('sapi5')
voices=engine.getProperty('voices')
engine.setProperty('voice',voices[0].id)

def speak(audio):
    engine.say(audio)
    engine.runAndWait()

def wishMe():
    hour=(datetime.datetime.now().hour)
    if hour>=0 and hour<12:
        speak("Good Morning!")
    elif hour>=12 and hour<18:
        speak("Good Afternoon!")
    else:
        speak("Good Evening!")
    speak("I am Jarvis Sir. Please tell me how may I help you")


def takeCommand():
    r=sr.Recognizer()
    with sr.Microphone() as source:
        
        print("Listening...")
        r.pause_threshold=1
        audio=r.listen(source)
        
    try:
        print("Recognizing...")
        query=r.recognize_google(audio,language='en-in')
        print(f"User said: {query}\n")
        
        
    except Exception as e:
        print("Say that again please...")
        speak("Say that again please...")
        if(count==1):
            print("Do You Want Anything Else Sir?")
            speak("Do You Want Anything Else Sir?")
            return 'None'
        count=1
        return 'None'
    return query
def sendEmail(to,content):
    file=open('file.txt','r')
    server= smtplib.SMTP('smtp.gmail.com',587)
    server.ehlo()
    server.starttls()
    server.login('rajarshig007@gmail.com',file.read())
    server.sendmail('rajarshig007@gmail.com',to,content)
    server.close()
    file.close()

if __name__=="__main__":
    wishMe()
    while True:
        query=takeCommand().lower()
        if(query=="no"):
            print("Ok then, have a GoodDay Sir!")
            speak("Ok then, have a GoodDay Sir!")
            break;
        if 'wikipedia' in query:
            print("Searching...")
            speak("Searching in Wikipedia")
            query=query.replace('wikipedia','')
            result=wikipedia.summary(f'{query}',sentences=2)
            speak("According to Wikipedia...")
            print(result)
            speak(result)

        elif 'open youtube' in query:
            speak("Opening Youtube...")
            webbrowser.open("https://www.youtube.com/")

        elif 'open google' in query:
            speak("Opening google...")
            webbrowser.open("https://www.google.com/")

        elif 'open stack overflow' in query:
            speak("Opening stackoverflow...")
            webbrowser.open("https://stackoverflow.com/")

        elif 'open facebook' in query:
            speak("Opening facebook...")
            webbrowser.open("https://www.facebook.com/")

        elif 'open whatsapp' in query:
            speak("Opening whatsapp...")
            webbrowser.open("https://web.whatsapp.com/")

        elif 'play music' in query:
            music_dir="C:\\Rajarshi\\Music"
            songs = os.listdir(music_dir)
            rand=random.randint(0,len(songs)-1)
            os.startfile(os.path.join(music_dir,songs[rand]))

        elif 'the time' in query:
            strTime=datetime.datetime.now().strftime("%H:%M:%S")
            speak(f"Sir, the time is {strTime}")

        elif 'the date' in query:
            strDate=datetime.datetime.now()
            speak(f"Sir, Today is {strDate.strftime('%A')},{strDate.strftime('%d')} of {strDate.strftime('%B')}")   

        elif 'open spotify' in query:
            speak("Opening spotify...")
            webbrowser.open("https://open.spotify.com/")

        elif 'weather today' in query:
            api_key = "Weather"  
            base_url = "http://api.openweathermap.org/data/2.5/weather?"
            city_name = "Kolkata"
            complete_url = base_url + "appid=" + '37bc6932970efee7d32d9960a2d7faf8' + "&q=" + city_name 
            response = requests.get(complete_url)
            x = response.json()
            if x["cod"] != "404":
                y = x["main"]
                z=x["weather"]
                weather= z[0]["description"]
                temp=int(y["temp"]-273.0)
                speak(f"Today's Temperature is {temp} degree celcius and the weather being {weather}")
        elif 'send an email' in query:
            try:
                speak("Whom should I Email?")
                mail=input("Email: ")
                speak("What should I say?")
                content=takeCommand()
                to= mail
                sendEmail(to,content)
                speak("Email sent")
            except Exception as e:
                print(e)
                print("Try Again")
        elif 'shutdown' in query:
            print("Shutting Down..")
            speak("Shutting Down, Have a Good Day, sir")
            break;