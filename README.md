<div align="center">

# Projet API SNCF - Android/Mobile

### Récupération des informations en temps réel sur les trains

<img alt="Android" src="https://img.shields.io/badge/-Android-9FC138?style=flat&logo=android&logoColor=white" />
<img alt="Android Studio" src="https://img.shields.io/badge/-Android_Studio-90BF58?style=flat&logo=android-studio&logoColor=white" />
<img alt="Java" src="https://img.shields.io/badge/-Java-E61F24?style=flat&logo=java&logoColor=white" />

</div>

<table>
    <thead>
        <tr>
            <th width="150px">Année</th>
            <th width="150px">Filière</th>
            <th width="300px">Matière</th>
            <th width="300px">Projet</th>
            <th width="350px">Collaborateurs</th>
        </tr>
    </thead>
    <tbody>
        <tr>
        <td align="center">2022-2023</td>
        <td align="center">M2 IWOCS</td>
        <td align="center">Programmation Android</td>
        <td align="center">API SNCF</td>
        <td align="center">Léa Gallier et Kévin Leroux</td>
        </tr>
    </tbody>
</table>

### Plan

1. [Présentation du projet](#présentation-du-projet)
2. [Résultat](#résultat)

## Présentation du projet

L'objectif de ce projet est de développer une application Android sous **Java** (ou Kotlin mais nous avons choisi Java) permettant de connaître les horaires de départ et d'arrivée des trains à la gare du Havre en utilisant l'**API** en temps réel de la **SNCF**.

Notre application va être structuré de la manière suivante : nous aurons deux onglets, un onglet pour les départs et un onglet pour les arrivées. Sur chaque onglet on ne va afficher sur les *10* prochains trains en fonction de la **date du jour** et de **l'heure actuelle**. Enfin, pour chaque train on affichera bien sûr la date et l'heure mais également sa **destination** (par exemple Paris pour les départs et bien évidemment Le Havre pour les trains d'arrivée), le **type** de train (TER, RER, ...) et son **numéro**.

Sur chaque onglet, nous aurons aussi la date du jour et l'heure actuelle d'affiché pour permettre à l'utilisateur d'être sûr de choisir le bon train.

## Résultat

<div align="center">
<img title="Preview for departures tab" src="preview_departures.png" alt="Preview | Departures" width="300px" />
<img title="Preview for arrivals tab" src="preview_arrivals.png" alt="Preview | Arrivals" width="300px" />
</div>

N.b. : source du logo de l'application : <a href="https://www.flaticon.com/fr/icones-gratuites/train-a-grande-vitesse" title="Icône | Train à grande vitesse">Train à grande vitesse icônes créées par le graphiste Freepik - Flaticon</a>
