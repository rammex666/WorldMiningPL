# 🌍 MiningWorld - Plugin Minecraft pour PinguLand 🇫🇷

Ce plugin a été conçu pour le serveur Minecraft **PinguLand** 🐧. Il permet de gérer des mondes miniers temporaires avec des fonctionnalités comme la création, la suppression et la réinitialisation automatique.

---

## ✨ Fonctionnalités principales
- 🌱 Création de mondes avec un seed personnalisé.
- 🗑️ Suppression propre des mondes inutilisés.
- 🔄 Réinitialisation automatique des mondes.
- ⚙️ Configuration via un fichier `config.yml`.

---

## 📦 Installation
1. Téléchargez le fichier `.jar` depuis la section *Releases*.
2. Placez-le dans le dossier `plugins` de votre serveur.
3. Redémarrez le serveur.
4. Configurez le plugin dans `config.yml`.

---

## 🛠️ Commandes
| Commande         | Description                          |
|------------------|--------------------------------------|
| `/mwpl`          | Commande principale du plugin.       |

---

## 📜 Configuration
Exemple de configuration dans `config.yml` :

```yaml
worlds:
  test:
    name: "Test World"
    seed: "2786386421968123439"
    timeBeforeReset: 86400 # 1 day in seconds


messages:
  deleteIn5s: "§cAttention ! Le monde {world} sera réinitialisé dans 5 secondes !"
  deleteIn3s: "§cAttention ! Le monde {world} sera réinitialisé dans 3 seconde !"
  deleteIn2s: "§cAttention ! Le monde {world} sera réinitialisé dans 2 seconde !"
  deleteIn1s: "§cAttention ! Le monde {world} sera réinitialisé dans 1 seconde !"
  worldReset: "§aLe monde {world} a été réinitialisé !"
```

<hr></hr>
🐧 À propos de PinguLand
PinguLand est un serveur Minecraft français offrant une expérience unique et conviviale. Rejoignez-nous !
<hr></hr> Merci d'utiliser MiningWorld et bon jeu ! 🎮✨
