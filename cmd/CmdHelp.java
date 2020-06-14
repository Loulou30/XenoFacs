package com.massivecraft.factions.cmd;

import java.util.ArrayList;

import com.massivecraft.factions.Conf;
import com.massivecraft.factions.P;
import com.massivecraft.factions.integration.Econ;
import com.massivecraft.factions.struct.Permission;


public class CmdHelp extends FCommand
{
	
	public CmdHelp()
	{
		super();
		this.aliases.add("help");
		this.aliases.add("h");
		this.aliases.add("?");
		
		//this.requiredArgs.add("");
		this.optionalArgs.put("page", "1");
		
		this.permission = Permission.HELP.node;
		this.disableOnLock = false;
		
		senderMustBePlayer = false;
		senderMustBeMember = false;
		senderMustBeOfficer = false;
		senderMustBeLeader = false;
	}	
	
	@Override
	public void perform()
	{
		if (helpPages == null) updateHelp();
		
		int page = this.argAsInt(0, 1);
		
		sendMessage(p.txt.titleize("XenoFacs aide ("+page+"/"+helpPages.size()+")"));
		
		page -= 1;
		
		if (page < 0 || page >= helpPages.size())
		{
			msg("<b>Cette page n'existe pas");
			return;
		}
		sendMessage(helpPages.get(page));
	}
	
	//----------------------------------------------//
	// Build the help pages
	//----------------------------------------------//
	
	public ArrayList<ArrayList<String>> helpPages;
	
	public void updateHelp()
	{
		helpPages = new ArrayList<ArrayList<String>>();
		ArrayList<String> pageLines;

		pageLines = new ArrayList<String>();
		//pageLines.add( p.cmdBase.cmdHelp.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdList.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdShow.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdPower.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdJoin.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdLeave.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdHome.getUseageTemplate(true) );
		pageLines.add( p.txt.parse("<i>Regardez comment cr�er une faction dans la prochaine page.") );
		helpPages.add(pageLines);
		
		pageLines = new ArrayList<String>();
		pageLines.add( p.cmdBase.cmdCreate.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdDescription.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdTag.getUseageTemplate(true) );
		pageLines.add( p.txt.parse("<i>Vous pouvez activer et d�sactiver les invitations :" ));
		pageLines.add( p.cmdBase.cmdOpen.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdInvite.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdDeinvite.getUseageTemplate(true) );
		pageLines.add( p.txt.parse("<i>Et n'oubliez pas de mettre un f home:" ));
		pageLines.add( p.cmdBase.cmdSethome.getUseageTemplate(true) );
		helpPages.add(pageLines);
		
		if (Econ.isSetup() && Conf.econEnabled && Conf.bankEnabled)
		{
			pageLines = new ArrayList<String>();
			pageLines.add( p.txt.parse("<i>Votre faction a aussi une banque qui permet de payer certaines" ));
			pageLines.add( p.txt.parse("<i>choses, il faudra donc y d�poser de l'argent." ));
			pageLines.add( p.txt.parse("<i>Pour en savoir plus, utilisez la commande money." ));
			pageLines.add( "" );
			pageLines.add( p.cmdBase.cmdMoney.getUseageTemplate(true) );
			pageLines.add( "" );
			pageLines.add( "" );
			pageLines.add( "" );
			helpPages.add(pageLines);
		}
		
		pageLines = new ArrayList<String>();
		pageLines.add( p.cmdBase.cmdClaim.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdAutoClaim.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdUnclaim.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdUnclaimall.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdKick.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdPromote.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdDemote.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdOfficer.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdLeader.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdTitle.getUseageTemplate(true) );
		pageLines.add( p.txt.parse("<i>Les titres des joueurs sont juste pour le plaisir. Aucune r�gle ne leur est li�e." ));
		helpPages.add(pageLines);
		
		pageLines = new ArrayList<String>();
		pageLines.add( p.cmdBase.cmdMap.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdSeeChunks.getUseageTemplate(true) );
		pageLines.add(p.txt.parse("<i>Les terres revendiqu�es dont la propri�t� est d�finie sont davantage prot�g�es afin"));
		pageLines.add(p.txt.parse("<i>que seuls le(s) propri�taire(s), l�administrateur de faction et �ventuellement"));
		pageLines.add(p.txt.parse("<i>les mod�rateurs de faction ont un acc�s complet."));
		helpPages.add(pageLines);
		
		pageLines = new ArrayList<String>();
		pageLines.add( p.cmdBase.cmdDisband.getUseageTemplate(true) );
		pageLines.add("");
		pageLines.add( p.cmdBase.cmdRelationAlly.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdRelationTruce.getUseageTemplate(true) ); 
		pageLines.add( p.cmdBase.cmdRelationNeutral.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdRelationEnemy.getUseageTemplate(true) );
		pageLines.add(p.txt.parse("<i>D�finissez la relation que vous souhaitez avoir avec une autre faction."));
		pageLines.add(p.txt.parse("<i>Votre relation par d�faut avec les autres factions sera neutre."));
		pageLines.add(p.txt.parse("<i>Si les DEUX factions choisissent \"ally\", vous serez des alli�s."));
		pageLines.add(p.txt.parse("<i>Si UNE faction choisit \"enemy\", vous serez ennemis."));
		helpPages.add(pageLines);
		
		pageLines = new ArrayList<String>();
		pageLines.add(p.txt.parse("<i>Vous ne pouvez jamais blesser des membres ou des alli�s."));
		pageLines.add(p.txt.parse("<i>Vous ne pouvez pas blesser les neutres sur leur propre territoire."));
		pageLines.add(p.txt.parse("<i>Vous pouvez toujours blesser les ennemis et les joueurs sans faction."));
		pageLines.add("");
		pageLines.add(p.txt.parse("<i>Les d�g�ts des ennemis sont r�duits sur votre propre territoire."));
		pageLines.add(p.txt.parse("<i>Quand vous mourez, vous perdez du power. Il est restaur� au fil du temps."));
		pageLines.add(p.txt.parse("<i>Le power d'une faction est la somme de tous les powers des membres."));
		pageLines.add(p.txt.parse("<i>Le power d'une faction d�termine la quantit� de terres qu'elle peut d�tenir."));
		pageLines.add(p.txt.parse("<i>Vous pouvez surclaim des terres � des factions avec trop peu de power."));
		helpPages.add(pageLines);
		
		pageLines = new ArrayList<String>();
		pageLines.add(p.txt.parse("<i>Seuls les membres de faction peuvent construire et d�truire dans leur propre"));
		pageLines.add(p.txt.parse("<i>territoire. L'utilisation des �l�ments suivants est �galement limit�e:"));
		pageLines.add(p.txt.parse("<i>Porte, coffre, four, distributeur."));
		pageLines.add("");
		pageLines.add(p.txt.parse("<i>Assurez-vous de placer des plaques de pression devant les portes pour vos"));
		pageLines.add(p.txt.parse("<i>visiteurs invit�s. Sinon, ils ne peuvent pas passer. Vous pouvez"));
		pageLines.add(p.txt.parse("<i>utilisez-le �galement pour cr�er des zones r�serv�es aux membres."));
		pageLines.add(p.txt.parse("<i>Les distributeurs �tant prot�g�s, vous pouvez cr�er des pi�ges sans"));
		pageLines.add(p.txt.parse("<i>s'inqui�ter du vol de ces fl�ches."));
		helpPages.add(pageLines);
		
		pageLines = new ArrayList<String>();
		pageLines.add(p.txt.parse("Bonus ; plus de traduction sera disponible dans le futur, attendez un peu !"));
		helpPages.add(pageLines);
		
		pageLines = new ArrayList<String>();
		pageLines.add("Les commandes pour les admins :");
		pageLines.add( p.cmdBase.cmdBypass.getUseageTemplate(true) );
		pageLines.add(p.txt.parse("<c>/f claim safezone <i>claim land for the Safe Zone"));
		pageLines.add(p.txt.parse("<c>/f claim warzone <i>claim land for the War Zone"));
		pageLines.add(p.txt.parse("<c>/f autoclaim [safezone|warzone] <i>take a guess"));
		pageLines.add(p.txt.parse("<i>Note: " + p.cmdBase.cmdUnclaim.getUseageTemplate(false) + P.p.txt.parse("<i>") + " works on safe/war zones as well."));
		helpPages.add(pageLines);
		
		pageLines = new ArrayList<String>();
		pageLines.add(p.txt.parse("<i>More commands for server admins:"));
		pageLines.add( p.cmdBase.cmdPowerBoost.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdLock.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdReload.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdSaveAll.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdVersion.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdConfig.getUseageTemplate(true) );
		helpPages.add(pageLines);
	}
}

