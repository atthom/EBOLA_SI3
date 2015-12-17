package model.entities;

import java.util.ArrayList;

import model.virus.Virus;

public final class Constantes {
	protected static final Virus VIRUS_PAR_DEFAUT = Virus.OK;
	protected static final double TAUX_ACTIVITE_DEFAUT = 0.5;
	protected static final double TAUX_ACTIVITE_MALADE_DEFAUT = 0.6;
	protected static final double TAUX_SOCIAL_DEFAUT = 0.9;
	protected static final ArrayList<Virus> VIRUS_POSSIBLE = new ArrayList<Virus>() {{
        add(Virus.H1N1);
        add(Virus.H5N1);
    }};
}
