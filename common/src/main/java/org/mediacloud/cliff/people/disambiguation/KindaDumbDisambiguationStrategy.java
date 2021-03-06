package org.mediacloud.cliff.people.disambiguation;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.mediacloud.cliff.extractor.PersonOccurrence;
import org.mediacloud.cliff.people.ResolvedPerson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Use simple case-insensitive text matching to match person occurrences
 */
public class KindaDumbDisambiguationStrategy implements PersonDisambiguationStrategy {

    private static final Logger logger = LoggerFactory
            .getLogger(KindaDumbDisambiguationStrategy.class);

    public KindaDumbDisambiguationStrategy() {
    }

    @Override
    public List<ResolvedPerson> select(List<PersonOccurrence> allPossibilities) {
        ArrayList<ResolvedPerson> bestCandidates = new ArrayList<ResolvedPerson>();
        for(PersonOccurrence occurrence: allPossibilities){
        	if (bestCandidates.size() == 0){
        		bestCandidates.add(new ResolvedPerson(occurrence));
        		continue;
        	}
        	boolean added = false;
        	
        	for(ResolvedPerson alreadyAdded: bestCandidates){
        		String newDude= occurrence.text.toLowerCase(Locale.US);
        		String oldDude = alreadyAdded.getName().toLowerCase(Locale.US);
        		if (oldDude.indexOf( newDude ) >=0 || newDude.indexOf(oldDude) >= 0){
        			logger.debug(alreadyAdded.getName() + " is a version of " + occurrence.text);
        			alreadyAdded.addOccurrence(occurrence);
        			added = true;
        			break;
        		}
        	}
        	if (!added){
        		bestCandidates.add(new ResolvedPerson(occurrence));
        	}
        	
        }
        return bestCandidates;
    }

    @Override
    public void logStats() {
        return;        
    }
}