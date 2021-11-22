import {Button, useTheme} from "@material-ui/core";
import CircularProgress from "@material-ui/core/CircularProgress";
import Typography from "@material-ui/core/Typography";
import React, {useEffect, useState} from "react";
import {useApi} from "../../Services/Hooks";
import PdfSelectionViewer from "../Utils/PDF/PdfSelectionViewer";

export default function ResumeListHardcoded({count, deniedCount}) {
    const api = useApi()
    const theme = useTheme()
    const [resumes, setResumes] = useState([])
    const [currentIndex, setCurrentIndex] = useState(0)
    const [isDeleting, setIsDeleting] = useState(false)
    const [buttonDeleting, setButtonDeleting] = useState(-1)

    useEffect(() => {
        api.get("/resumes")
            .then(r => setResumes(r ? r.data : []))
    }, []) // eslint-disable-line react-hooks/exhaustive-deps

    function deleteResume(index) {
        const nextState = [...resumes]
        return api.delete("/resumes/" + nextState[index].id)
            .then(() => {
                nextState.splice(index, 1)

                if (currentIndex >= nextState.length)
                    setCurrentIndex(0)

                setResumes(nextState)
            })
    }

    function getResumeState(resume) {
        if (resume.reviewState === "PENDING")
            return <span style={{color: theme.palette.info.main}}>En attente</span>
        else if (resume.reviewState === "DENIED")
            return <>
                <span style={{color: theme.palette.error.main}}>Rejeté : </span>
                {resume.reasonForRejection}
            </>
        else
            return <span style={{color: theme.palette.success.main}}>Approuvé</span>
    }

    return <div style={{height: "100%"}}>
        <PdfSelectionViewer documents={resumes.map(o => o.file)} title={"Liste des CVs"}>
            {(i, setCurrent) => <div key={i}>
                <Button
                    variant={i === currentIndex ? "contained" : "outlined"}
                    color={"primary"}
                    style={{marginBottom: 10}}
                    onClick={() => {
                        setCurrentIndex(i)
                        setCurrent(i)
                    }}>
                    <Typography variant={"body1"}
                                style={{padding: "0 2em 0 2em"}}
                                display={"block"}>
                        {resumes[i].name + " "}
                    </Typography>
                </Button>
                <Typography
                    variant={"body2"}>
                    État : {getResumeState(resumes[i])}
                    &emsp;
                    {i === currentIndex &&
                    <Button
                        variant={"contained"}
                        color={"secondary"}
                        size={"small"}
                        onClick={() => {
                            setIsDeleting(true)
                            setButtonDeleting(i)
                            deleteResume(i).then(() => {
                                setIsDeleting(false)
                                setButtonDeleting(-1)
                            })
                        }}
                        disabled={(isDeleting && buttonDeleting === i)}
                        title={""}>
                        <i className="fa fa-trash"/>&ensp;Supprimer
                    </Button>}
                    {isDeleting && buttonDeleting === i && <CircularProgress size={18}/>}
                </Typography>
                <hr/>
            </div>}
        </PdfSelectionViewer>
    </div>
}