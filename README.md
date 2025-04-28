# 🎮 DiscordToChat

![Build Status](https://img.shields.io/badge/build-passing-brightgreen.svg) ![Java 8+](https://img.shields.io/badge/Java-8%2B-blue.svg) ![Spigot/Paper 1.16+](https://img.shields.io/badge/Spigot%2FPaper-1.16%2B-important.svg) ![License: MIT](https://img.shields.io/badge/License-MIT-green.svg)

Un plugin leggero e “senza fronzoli” per **replicare** in tempo reale i messaggi di un canale Discord nella chat del tuo server Minecraft (Spigot o Paper 1.16+).

---

## 🚀 Caratteristiche principali

- 🔄 **Relay in tempo reale** dei messaggi Discord → Minecraft.  
- 🤖 Ignora automaticamente i messaggi di altri bot.  
- 🎯 Filtra solo un canale Discord specifico (configurabile).  
- 🔧 **Formato personalizzabile** di output in chat Minecraft.  
- 📂 **Zero dipendenze** esterne oltre a JDA e Spigot API.  
- ⚙️ Facile da modificare per aggiungere filtri o comandi extra.

---

## 🛠 Prerequisiti

- **Java 8** o superiore  
- **Spigot** o **Paper** 1.16+  
- **Maven** _o_ **Gradle** per compilare  
- Un **bot Discord** attivo (token + permessi di lettura)

---

## ⚙️ Installazione

1. **Clona** il repository:

    ```bash
    git clone https://github.com/IlTuoNome/DiscordToChat.git
    cd DiscordToChat
    ```

2. **Configura le dipendenze**  
   - **Maven**: controlla `pom.xml` (Spigot API provided, JDA).  
   - **Gradle**: controlla `build.gradle` (stesse dipendenze).

3. **Compila** il plugin:

    ```bash
    # Con Maven
    mvn clean package

    # Oppure con Gradle
    gradle build
    ```

4. **Copia** il file JAR generato nella cartella `plugins/` del tuo server:

   - Se usi Maven:  
     `target/DiscordToChat-1.0.jar → plugins/DiscordToChat.jar`

   - Se usi Gradle:  
     `build/libs/DiscordToChat-1.0.jar → plugins/DiscordToChat.jar`

5. **Avvia** o **riavvia** il server Minecraft.

---

## 🔧 Configurazione

Al primo avvio si genererà la cartella `plugins/DiscordToChat/` con dentro `config.yml`. Apri quel file e modificalo così:

```yaml
# Token del bot Discord (senza “Bot ” davanti)
token: "TUO_DISCORD_BOT_TOKEN"

# ID numerico del canale Discord da cui leggere i messaggi
channel-id: 123456789012345678

# Formato del messaggio in chat Minecraft
format: "[Discord] <%AUTHOR%>: %MESSAGE%"
