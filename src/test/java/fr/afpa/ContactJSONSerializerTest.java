package fr.afpa;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import fr.afpa.models.Contact;

/**
 * Ceci est une classe de tests qui ne sera pas intégrée à l'application finale livrée au client.
 * 
 * Il s'agit d'une classe permettant d'intégrer les tests unitaires.
 * 
 * Son objectif est de tester les méthodes de la classe "ContactJSONSerializer".
 * 
 * Les tests unitaires sont très intéressant pour tester l'application sans avoir 
 * à interagir avec l'interface graphique.
 * 
 * Ils pourront, nous le verrons, être lancé automatiquement par certains outils
 * (on parle alors d'intégration continue, domaine du "DevOps").
 * Exemple d'outils d'automatisation de tests unitaires :
 * - Jenkins 
 * - Github Action
 * - Gitlab CI
 * - ...
 * 
 * Les tests unitaires sont FONDAMENTAUX et permettent de favoriser la qualité logicielle.
 * 
 */
public class ContactJSONSerializerTest {
    
    /// Objectif : on va tester la méthode "save" de "ContactJSONSerialize"
    // On doit réfléchir à plusieurs choses :
    // - quelles sont les paramètres d'entrées (donnée entrantes)
    // - que renvoie la fonction ? Quel est son résultat ?
    // - dans quel cas le test est réussi ?
    //
    // Il va nous falloir coder tout ça !

    // L'annotation "@Test" est à ajouter sur toutes les fonctions
    // qui auront pour objectif de tester d'autre méthodes
    @Test
    public void testJSONSave() {
        // Ici on va implémenter la logique du test 
        // on va vérifier que tout se passe bien

        // Etape 1 : on appel la méthode ciblée par le test
        // on doit instancier le "Serializer"
        ContactJSONSerializer serializer = new ContactJSONSerializer();

        Contact contactTest = new Contact("Michel", "MICHEL", "0606060606", "miche@michel.com",
        "36 rue du trente-six", "33360", Contact.Gender.MALE, null, null, null, null);
        
        // lancement du test 
        serializer.save("test.json", contactTest);

        // ICI il faudrait vérifier la méthode a bien fait
        // ce qu'elle devait faire

        // dans notre cas --> la méthode a-t-elle correctement créé le fichier json ?

        // Pour attester qu'un test s'est bien déroulé (ou non)
        // il faut utiliser le système d'assertions

        assertTrue(false);

        // Beaucoup d'assert sont utilisables
        // ^parmi les plus utilisés on retrouve "assertEquals" pour vérifier l'égalité
        // assertEquals(serializer, contactTest);
    }
    

}
