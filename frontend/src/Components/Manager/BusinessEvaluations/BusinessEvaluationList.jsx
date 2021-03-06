import {Divider} from "@material-ui/core";
import Button from "@material-ui/core/Button";
import CircularProgress from "@material-ui/core/CircularProgress";
import Grid from "@material-ui/core/Grid";
import Typography from "@material-ui/core/Typography";
import React, {useEffect, useState} from "react";
import {useApi} from "../../../Services/Hooks";
import useStyles from "../../Utils/Style/useStyles";

export default function BusinessEvaluationList() {
    const classes = useStyles()
    const api = useApi()
    const [businessEvaluations, setBusinessEvaluations] = useState([])
    const [currentIndex, setCurrentIndex] = useState(0)
    const [isDeleting, setIsDeleting] = useState(false)
    const [evaluationDeleting, setEvaluationDeleting] = useState(-1)

    useEffect(() => {
        api.get("/businessEvaluation")
            .then(r => setBusinessEvaluations(r ? r.data : []))
    }, []) // eslint-disable-line react-hooks/exhaustive-deps

    function deleteBusinessEvaluation(index) {
        const nextState = [...businessEvaluations]
        return api.delete("/businessEvaluation/" + nextState[index].id)
            .then(() => {
                nextState.splice(index, 1)
                setBusinessEvaluations(nextState)
            })
    }

    return <Grid
        container
        spacing={0}
        className={classes.main}
        style={{padding: "15px 0 0 15px"}}
    >
        <Grid item xs={5} className={classes.list}>
            <Typography variant={"h4"} gutterBottom={true} className={classes.title}>
                √Čvaluations des milieux de stage
            </Typography>
            {businessEvaluations.length > 0 ? businessEvaluations.map((item, i) =>
                <div key={i}>
                    <Button
                        variant={currentIndex === i ? "contained" : "outlined"}
                        color={"primary"}
                        size={"large"}
                        onClick={() => {
                            setCurrentIndex(i)
                        }}>
                        <Typography variant={"button"} display={"block"}>
                            {item.contract.studentApplication.student.firstName +
                            " " + item.contract.studentApplication.student.lastName} -&ensp;
                            {item.contract.studentApplication.offer.employer.companyName}

                        </Typography>
                    </Button>
                    &ensp;
                    <Button
                        variant={currentIndex === i ? "contained" : "outlined"}
                        color={"secondary"}
                        size={"small"}
                        disabled={isDeleting}
                        onClick={() => {
                            setIsDeleting(true)
                            setEvaluationDeleting(i)
                            deleteBusinessEvaluation(i).then(() => {
                                setIsDeleting(false)
                                setEvaluationDeleting(-1)
                            })
                        }}
                    >
                        <i className="fa fa-trash" style={{color: "white"}}/>&ensp;
                        Supprimer l'√©valuation
                    </Button>
                    {isDeleting && evaluationDeleting === i && <CircularProgress size={18}/>}
                    <Divider className={classes.dividers}/>
                </div>
            ) : <Typography align="center">Aucun √©l√©ment √† afficher</Typography>}
        </Grid>
        <Grid item xs={7} align="start" style={{overflow: "auto", height: "100%"}}>
            <div>
                {businessEvaluations.map((item, i) =>
                    <div key={i}>
                        {currentIndex === i &&
                        <div>
                            <Typography variant="h5">
                                IDENTIFICATION DE L‚ÄôENTREPRISE
                            </Typography>
                            <Typography>
                                <strong>Nom de l‚Äôentreprise : </strong>
                                {businessEvaluations[currentIndex].contract.studentApplication.offer.employer.companyName}
                            </Typography>
                            <Typography>
                                <strong>Repr√©sentant de l'entreprise : </strong>
                                {businessEvaluations[currentIndex].contract.studentApplication.offer.employer.contactName}
                            </Typography>
                            <Typography>
                                <strong>Adresse : </strong>
                                {businessEvaluations[currentIndex].contract.studentApplication.offer.employer.address}
                            </Typography>
                            <Typography>
                                <strong>Adresse courriel : </strong>
                                {businessEvaluations[currentIndex].contract.studentApplication.offer.employer.email}
                            </Typography>
                            <Typography>
                                <strong>T√©l√©phone : </strong>
                                {businessEvaluations[currentIndex].contract.studentApplication.offer.employer.phoneNumber}
                            </Typography>
                            <Divider className={classes.dividers}/>
                            <Typography>
                                <strong>Offre : </strong>
                                {item.contract.studentApplication.offer.title}
                            </Typography>
                            <Divider className={classes.dividers}/>
                            <Typography variant="h5">
                                IDENTIFICATION DU STAGIAIRE
                            </Typography>
                            <Typography>
                                <strong>Nom du stagiaire : </strong>
                                {businessEvaluations[currentIndex].contract.studentApplication.student.firstName} {businessEvaluations[currentIndex].contract.studentApplication.student.lastName}
                            </Typography>
                            <Typography>
                                <strong>Date du stage : </strong>
                                {businessEvaluations[currentIndex].contract.studentApplication.offer.details.internshipStartDate}
                            </Typography>
                            <Typography>
                                <strong>Stage : </strong>
                                {businessEvaluations[currentIndex].evaluationCriterias.internshipCount}
                            </Typography>
                            <Divider className={classes.dividers}/>
                            <Typography variant="h5">
                                √ČVALUATION
                            </Typography>
                            <Typography>
                                <strong>Les t√Ęches confi√©es au stagiaire sont conformes aux t√Ęches annonc√©es dans
                                    l‚Äôentente de stage : </strong>
                                {businessEvaluations[currentIndex].evaluationCriterias.workAsAnnoncement}
                            </Typography>
                            <Typography>
                                <strong>Des mesures d‚Äôaccueil facilitent l‚Äôint√©gration du nouveau stagiaire : </strong>
                                {businessEvaluations[currentIndex].evaluationCriterias.easyIntigration}
                            </Typography>
                            <Typography>
                                <strong>Le temps r√©el consacr√© √† l‚Äôencadrement du stagiaire est suffisant : </strong>
                                {businessEvaluations[currentIndex].evaluationCriterias.sufficientTime}
                            </Typography>
                            <Typography>
                                <strong>L‚Äôenvironnement de travail respecte les normes d‚Äôhygi√®ne et de s√©curit√© au
                                    travail : </strong>
                                {businessEvaluations[currentIndex].evaluationCriterias.securityWorkPlace}
                            </Typography>
                            <Typography>
                                <strong>Le climat de travail est agr√©able : </strong>
                                {businessEvaluations[currentIndex].evaluationCriterias.pleasantEnvironnement}
                            </Typography>
                            <Typography>
                                <strong>Le milieu de stage est accessible par transport en commun : </strong>
                                {businessEvaluations[currentIndex].evaluationCriterias.accessiblePlace}
                            </Typography>
                            <Typography>
                                <strong>Le salaire offert {businessEvaluations[currentIndex].evaluationCriterias.salary}
                                    est int√©ressant pour le stagiaire : </strong>
                                {businessEvaluations[currentIndex].evaluationCriterias.goodSalary}
                            </Typography>
                            <Typography>
                                <strong>La communication avec le superviseur de stage facilite le d√©roulement du
                                    stage : </strong>
                                {businessEvaluations[currentIndex].evaluationCriterias.supervisorFacilitatesIntern}
                            </Typography>
                            <Typography>
                                <strong>L‚Äô√©quipement fourni est ad√©quat pour r√©aliser les t√Ęches confi√©es : </strong>
                                {businessEvaluations[currentIndex].evaluationCriterias.adequateEquipement}
                            </Typography>
                            <Typography>
                                <strong>Le volume de travail est acceptable : </strong>
                                {businessEvaluations[currentIndex].evaluationCriterias.accetableWorkload}
                            </Typography>
                            <Typography>
                                <strong>Pr√©ciser le nombre d‚Äôheures par semaine : </strong>
                                Premier mois : 
                                {businessEvaluations[currentIndex].evaluationCriterias.hoursOfWeekFirstMonth}h.
                                Deuxi√®me mois : 
                                {businessEvaluations[currentIndex].evaluationCriterias.hoursOfWeekFirstMonth}h.
                                Troisi√®me mois : 
                                {businessEvaluations[currentIndex].evaluationCriterias.hoursOfWeekFirstMonth}h.
                            </Typography>
                            <Typography>
                                <strong>Commentaires : </strong>
                                {businessEvaluations[currentIndex].evaluationCriterias.comment}
                            </Typography>
                            <Divider className={classes.dividers}/>
                            <Typography variant="h5">
                                OBSERVATIONS G√ČN√ČRALES
                            </Typography>
                            <Typography>
                                <strong>Ce milieu est √† privil√©gier pour le : </strong>
                                {businessEvaluations[currentIndex].observations.preferedInternship}
                            </Typography>
                            <Typography>
                                <strong>Ce milieu est ouvert √† accueillir : </strong>
                                {businessEvaluations[currentIndex].observations.numbersOfInterns}
                            </Typography>
                            <Typography>
                                <strong>Ce milieu d√©sire accueillir le m√™me stagiaire pour un prochain stage : </strong>
                                {businessEvaluations[currentIndex].observations.welcomeSameIntern}
                            </Typography>
                            <Typography>
                                <strong>Ce milieu offre des quarts de travail variables : </strong>
                                {businessEvaluations[currentIndex].observations.variablesShifts}.
                                De {businessEvaluations[currentIndex].observations.startShiftsOne}h
                                √† {businessEvaluations[currentIndex].observations.endShiftsOne}h
                                De {businessEvaluations[currentIndex].observations.startShiftsTwo}h
                                √† {businessEvaluations[currentIndex].observations.endShiftsTwo}h
                                De {businessEvaluations[currentIndex].observations.startShiftsThree}h
                                √† {businessEvaluations[currentIndex].observations.endShiftsThree}h
                            </Typography>
                            <Divider className={classes.dividers}/>
                            <Typography variant="h5">
                                Signature du gestionnaire du stage
                            </Typography>
                            <img src={businessEvaluations[currentIndex].signature.image}
                                 alt="signature"
                                 className={classes.signature}
                            />
                            <Divider className={classes.dividers}/>
                            <Typography>
                                <strong>Enseignant responsable : </strong>
                                {businessEvaluations[currentIndex].signature.name}
                            </Typography>
                            <Typography>
                                <strong>Date : </strong>
                                {businessEvaluations[currentIndex].signature.date}
                            </Typography>
                        </div>
                        }
                    </div>
                )}
            </div>
        </Grid>
    </Grid>
}